package de.codecentric.microplode.messaging.api;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value="EventMyOne")
public class Event {

    private EventType type;
    
    private List<PlayerDef> players = new ArrayList<PlayerDef>();

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public List<PlayerDef> getPlayers() {
        return players;
    }

    public void addPlayers(PlayerDef player) {
        players.add(player);
    }
}
