package de.codecentric.microplode.action;


import de.codecentric.microplode.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardAction {

    @Autowired
    private Board board;


    public void newGame() {
        board.init();
        board.debugOut();
    }

}
