package at.htl.ondemand.service;

import at.htl.ondemand.model.DisplayEvent;
import at.htl.ondemand.model.DisplayEventType;
import at.htl.ondemand.model.OverlaySession;
import at.htl.ondemand.model.SessionState;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Singleton
public class SessionService {

    final Map<UUID, OverlaySession> sessions = new ConcurrentHashMap<>();

    @Inject
    volatile Logger log;

    @Inject
    XiboService xiboService;

    @Inject
    @Channel("display-event")
    @Broadcast
    Emitter<DisplayEvent> displayEventEmitter;

    @PostConstruct
    void init() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new SessionTimer(), 0, 1, TimeUnit.SECONDS);
    }

    public void addSession(Long layoutId, Long displayId, UUID uuid, Integer duration) {
        OverlaySession session = new OverlaySession(layoutId, displayId, duration);
        this.sessions.put(uuid, session);
        this.displayEventEmitter.send(new DisplayEvent(session, DisplayEventType.CHANGE));
    }

    public boolean getAndFinishSession(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);

        OverlaySession overlaySession = this.sessions.get(uuid);
        if (overlaySession == null) {
            return false;
        }

        // This is the first request from the player. After this the player is playing the video
        if (overlaySession.state == SessionState.INITIAL) {
            overlaySession.state = SessionState.STARTED;
            this.displayEventEmitter.send(new DisplayEvent(overlaySession, DisplayEventType.CHANGE));
            return true;
        }

        // If the player requests again after initial he will be started or finished. Either way he should not play again
        if (overlaySession.state == SessionState.STARTED) {
            overlaySession.state = SessionState.FINISHED;
            this.displayEventEmitter.send(new DisplayEvent(overlaySession, DisplayEventType.CHANGE));
        }

        return false;
    }

    public void removeLayout(OverlaySession session) {
        this.xiboService.deleteLayout(session.layoutId);
        this.displayEventEmitter.send(new DisplayEvent(session, DisplayEventType.DELETE));
    }

    public boolean deleteSession(UUID uuid) {
        OverlaySession session = this.sessions.remove(uuid);
        if (session == null) {
            return false;
        }

        this.removeLayout(session);
        return true;
    }

    public UUID getdisplayById(Long displayId) {
        for (Map.Entry<UUID, OverlaySession> uuidOverlaySessionEntry : this.sessions.entrySet()) {
            if (uuidOverlaySessionEntry.getValue().displayId.equals(displayId)) {
                return uuidOverlaySessionEntry.getKey();
            }
        }

        return null;
    }

    @Incoming("display-event")
    public void processEvent(DisplayEvent event) {
    }

    private class SessionTimer implements Runnable {
        @Override
        public void run() {
            LocalDateTime now = LocalDateTime.now();

            // If something got schedule but never got in touch with the server, remove it later
            Iterator<Map.Entry<UUID, OverlaySession>> iterator = SessionService.this.sessions.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<UUID, OverlaySession> entry = iterator.next();
                OverlaySession overlaySession = entry.getValue();
                if (overlaySession.state == SessionState.FINISHED || overlaySession.createdAt
                        .plusSeconds(overlaySession.duration)
                        .plusMinutes(10)
                        .isBefore(now)) {
                    iterator.remove();
                    SessionService.this.removeLayout(overlaySession);
                }
            }

            String collect = SessionService.this.sessions.keySet().stream().map(UUID::toString).collect(Collectors.joining(" "));
            SessionService.this.log.infov("Current Session [{0}]", collect);
        }
    }
}
