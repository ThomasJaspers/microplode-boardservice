package de.codecentric.microplode.messaging.api;

public enum EventType {

    MOVE_EVENT("move-event"),
    INITIALIZE_EVENT("initialize-event"),
    BOARD_CHANGED_EVENT("board-changed-event");
    
    private String text;

    private EventType(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
}
