package at.htl.ondemand.model;

public class DisplayEvent {
    public OverlaySession session;
    public DisplayEventType type;

    public DisplayEvent(OverlaySession session, DisplayEventType type) {
        this.session = session;
        this.type = type;
    }
}
