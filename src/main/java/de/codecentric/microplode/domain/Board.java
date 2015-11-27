package de.codecentric.microplode.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Board {

    private static final int BOARD_SIZE = 100;

    private List<Field> board;

    @PostConstruct
    public void init() {
        board = new ArrayList<Field>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            board.add(new Field(i));
        }
    }

    public List<Field> getBoard() {
        return board;
    }

    public void debugOut() {

        System.out.println();
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (i % 10 == 0) {
                System.out.println();
            }

            Field field = getBoard().get(i);
            if (field.getLoad() > 0) {
                System.out.print(field.getOccupiedBy().getRepresentation() + " ");
            }
            System.out.print(field.getLoad() + "\t");
        }
    }
}