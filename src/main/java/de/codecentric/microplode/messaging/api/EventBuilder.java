package de.codecentric.microplode.messaging.api;

public class EventBuilder {

    public static BoardChangedEvent newGameEvent() {
        BoardChangedEvent newGameEvent = new BoardChangedEvent();
    /*    newGameEvent.setType(EventType.NEW_GAME_EVENT);
        MoveDef playerOne = new MoveDef();
        playerOne.setId("Kurt");
        playerOne.setType(PlayerType.HUMAN);
        newGameEvent.addPlayers(playerOne);
        
        MoveDef playerTwo = new MoveDef();
        playerTwo.setId("HighEndMac");
        playerTwo.setType(PlayerType.COMPUTER);
        newGameEvent.addPlayers(playerTwo);*/
        return newGameEvent;
    }
}
