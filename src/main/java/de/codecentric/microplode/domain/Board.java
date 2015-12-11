package de.codecentric.microplode.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Board {

    public static final int BOARD_WIDTH = 10;

    public static final int BOARD_HEIGHT = 10;

    private Field[][] board;

    @PostConstruct
    public void init() {
        board = new Field[BOARD_WIDTH][BOARD_HEIGHT];
        for (int x = 0; x < BOARD_WIDTH; x++){
            for (int y = 0; y < BOARD_HEIGHT; y++){
                board[x][y] = new Field(x, y);
            }
        }

    }

    public boolean isFieldEmpty(Field targetField) {
        Field field = getBoard()[targetField.getPosX()][targetField.getPosY()];
        return field.isEmpty();
    }

    public boolean isFieldOccupiedByPlayer(Field targetField, PlayerInfo player) {
        Field field = getBoard()[targetField.getPosX()][targetField.getPosY()];
        return field.isOccupiedByPlayer(player);
    }


    public void executeMove(Field targetField, PlayerInfo player) {
        Field field = getBoard()[targetField.getPosX()][targetField.getPosY()];
        field.setOccupiedBy(player);
        field.increaseLoad();
        handleExplosions(player);
    }

    public Field[][] getBoard() {
        return board;
    }

    private void handleExplosions(PlayerInfo activePlayer) {

        Field field = findFieldToExplode();
        while (field != null) {
            performExplosion(field, activePlayer);
            field = findFieldToExplode();
        }
    }

    private Field findFieldToExplode() {
        Field field = null;

        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                Field testField = board[x][y];
                if (field == null && shouldExplode(testField)) {
                    field = testField;
                }
            }
        }

        return field;
    }


    private boolean shouldExplode(Field field) {

        if (isCornerField(field) && field.getLoad() > 3) {
            return true;
        }

        if (isEdgeField(field) && field.getLoad() > 5) {
            return true;
        }

        if (field.getLoad() > 8) {
            return true;
        }

        return false;
    }

    private void performExplosion(Field fieldToExplode, PlayerInfo player) {

        Field field = getBoard()[fieldToExplode.getPosX()][fieldToExplode.getPosY()];

        if (isCornerField(field)) {
            field.decreaseLoadBy(3);
        } else if (isEdgeField(field)) {
            field.decreaseLoadBy(5);
        } else {
            field.decreaseLoadBy(8);
        }

        handleExplosionForNeighbouringField(field.getPosX(), field.getPosY(), 1, -1, player);
        handleExplosionForNeighbouringField(field.getPosX(), field.getPosY(), 1, 0, player);
        handleExplosionForNeighbouringField(field.getPosX(), field.getPosY(), 1, 1, player);
        handleExplosionForNeighbouringField(field.getPosX(), field.getPosY(), 0, 1, player);
        handleExplosionForNeighbouringField(field.getPosX(), field.getPosY(), -1, 1, player);
        handleExplosionForNeighbouringField(field.getPosX(), field.getPosY(), -1, 0, player);
        handleExplosionForNeighbouringField(field.getPosX(), field.getPosY(), -1, -1, player);
        handleExplosionForNeighbouringField(field.getPosX(), field.getPosY(), 0, -1, player);
    }

    private boolean isCornerField(Field field) {
        if (field.getPosX() == 0 && field.getPosY() == 0
                || field.getPosX() == BOARD_WIDTH-1 && field.getPosY() == 0
                || field.getPosX() == 0 && field.getPosY() == BOARD_HEIGHT-1
                || field.getPosX() == BOARD_WIDTH-1 && field.getPosY() == BOARD_HEIGHT-1) {
            return true;
        } else {
            return false;
        }
    }

    // Note: A corner-field is also always an edge-field
    private boolean isEdgeField(Field field) {
        if (field.getPosY() - 1 < 0 || field.getPosY() + 1 >= BOARD_HEIGHT
                || field.getPosX() - 1 < 0 || field.getPosX() + 1 >= BOARD_WIDTH) {
            return true;
        } else {
            return false;
        }
    }

    private void handleExplosionForNeighbouringField(int xPos, int yPos, int xOffset, int yOffset, PlayerInfo player) {
        if (xPos + xOffset >= 0 && xPos + xOffset < BOARD_WIDTH
                && yPos + yOffset >= 0 && yPos + yOffset < BOARD_HEIGHT) {
            Field targetField = getBoard()[xPos + xOffset][yPos + yOffset];
            targetField.setOccupiedBy(player);
            targetField.increaseLoad();
        }
    }

    public void debugOut() {
        System.out.println();
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                Field field = board[x][y];
                if (field.getLoad() > 0) {
                    System.out.print(field.getOccupiedBy().getRepresentation() + " ");
                }
                System.out.print(field.getLoad() + "\t");
            }
            System.out.println();
        }
    }

}