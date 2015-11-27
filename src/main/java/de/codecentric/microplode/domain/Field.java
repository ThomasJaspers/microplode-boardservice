package de.codecentric.microplode.domain;


public class Field {

    private int fieldNum;

    private int load;

    private PlayerInfo occupiedBy;
    
    public Field(int fieldNum) {
        this.fieldNum = fieldNum;
        this.load = 0;
    }

    public int getFieldNum() {
        return fieldNum;
    }

    public PlayerInfo getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(PlayerInfo occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }
}