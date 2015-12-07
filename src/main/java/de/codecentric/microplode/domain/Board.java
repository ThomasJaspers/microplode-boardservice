package de.codecentric.microplode.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Board {

    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 10;

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

    public Field[][] getBoard() {
        return board;
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