package at.htl.ondemand.model;

import java.time.LocalDateTime;

public class OverlaySession {
    public Long layoutId;
    public Long displayId;
    public Integer duration;
    public LocalDateTime createdAt;
    public SessionState state;

    public OverlaySession(Long layoutId, Long displayId, Integer duration) {
        this.layoutId = layoutId;
        this.displayId = displayId;
        this.duration = duration;
        this.createdAt = LocalDateTime.now();
        this.state = SessionState.INITIAL;
    }
}
