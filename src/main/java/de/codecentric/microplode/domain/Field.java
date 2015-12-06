package de.codecentric.microplode.domain;


public class Field {

    private int posX;

    private int posY;

    private int load;

    private PlayerInfo occupiedBy;
    
    public Field(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.load = 0;
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

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}