package de.codecentric.microplode.domain;


import org.apache.commons.lang.StringUtils;

public class Field {

    private int posX;

    private int posY;

    private int load;

    private PlayerInfo occupiedBy;


    public void increaseLoad() {
        load++;
    }

    public void decreaseLoadBy(int num) { load -= num; }

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

    public boolean isEmpty() {
        if (occupiedBy == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOccupiedByPlayer(PlayerInfo player) {
        if (isEmpty()) {
            return false;
        }

        if (StringUtils.equals(getOccupiedBy().getId(), player.getId())) {
            return true;
        } else {
            return false;
        }
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