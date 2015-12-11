package de.codecentric.microplode.messaging.api;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

public class BoardChangedContainerEvent {

    private BoardChangedEvent event;

    public BoardChangedEvent getEvent() {
        return event;
    }

    public void setEvent(BoardChangedEvent event) {
        this.event = event;
    }
}
