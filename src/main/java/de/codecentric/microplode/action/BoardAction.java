package de.codecentric.microplode.action;


import de.codecentric.microplode.configuration.Queues;
import de.codecentric.microplode.domain.Board;
import de.codecentric.microplode.domain.Field;
import de.codecentric.microplode.domain.PlayerInfo;
import de.codecentric.microplode.messaging.MessageSender;
import de.codecentric.microplode.messaging.api.BoardChangedEvent;
import de.codecentric.microplode.messaging.api.EventType;
import de.codecentric.microplode.messaging.api.FieldDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoardAction {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private Board board;

    public void initialize() {
        board.init();
        board.debugOut();
        sendBoardChangedEvent();
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
        board.debugOut();
        sendBoardChangedEvent();
    }

    public void sendBoardChangedEvent() {
        BoardChangedEvent event = new BoardChangedEvent();
        event.setType(EventType.BOARD_CHANGED_EVENT);

        Field[][] board = this.board.getBoard();
        for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
            for (int x = 0; x < Board.BOARD_WIDTH; x++) {
                Field field = board[x][y];
                FieldDef fieldDef = new FieldDef();

                if (field.getOccupiedBy() != null) {
                    fieldDef.setPlayerId(field.getOccupiedBy().getId());
                }

                fieldDef.setLoad(field.getLoad());
                fieldDef.setCol(field.getPosX());
                fieldDef.setRow(field.getPosY());

                event.addField(fieldDef);
            }
        }

        messageSender.sendMessage(Queues.BOARDSERVICE_GAMESERVICE, event);
        messageSender.sendMessage(Queues.BOARDSERVICE_PRESENTATIONSERVICE, event);
    }
}
