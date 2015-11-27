package de.codecentric.microplode.domain;


public class PlayerInfo {

    private String id;

    private String representation;

    public PlayerInfo(String id, String representation) {
        this.id = id;
        this.representation = representation;
    }

    public String getId() {
        return id;
    }

    public String getRepresentation() {
        return representation;
    }
}