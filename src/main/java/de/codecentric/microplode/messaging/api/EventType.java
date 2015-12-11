package de.codecentric.microplode.messaging.api;

public enum EventType {

    MOVE_EVENT("move"),
    INITIALIZE_EVENT("initialize"),
    BOARD_CHANGED_EVENT("board-changed");
    
    private String text;

    private EventType(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
}
