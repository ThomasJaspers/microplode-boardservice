package de.codecentric.microplode.action;


import de.codecentric.microplode.domain.Board;
import de.codecentric.microplode.domain.Field;
import de.codecentric.microplode.domain.PlayerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardAction {

    @Autowired
    private Board board;

    public void initialize() {
        board.init();
        board.debugOut();
    }

    public boolean isMoveValid(Field targetField, PlayerInfo activePlayer) {
        if (board.isFieldEmpty(targetField) || board.isFieldOccupiedByPlayer(targetField, activePlayer)) {
            return true;
        } else {
            return false;
        }
    }

    public void makeMove(Field targetField, PlayerInfo activePlayer) {
        board.executeMove(targetField, activePlayer);
    }
}
