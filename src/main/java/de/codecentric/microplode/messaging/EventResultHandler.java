package de.codecentric.microplode.messaging;

import de.codecentric.microplode.action.BoardAction;
import de.codecentric.microplode.configuration.Queues;
import de.codecentric.microplode.domain.Field;
import de.codecentric.microplode.domain.PlayerInfo;
import de.codecentric.microplode.messaging.api.EventType;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EventResultHandler {

    @Autowired
    private BoardAction boardAction;
    
    @RabbitListener(queues=Queues.GAMESERVICE_BOARDSERVICE)
    public void handleMessage(@Payload String event) {
        System.out.println("Event received");

        JSONObject jsonRoot = new JSONObject(event);
        JSONObject jsonEvent = jsonRoot.getJSONObject("event");
        String eventType = jsonEvent.getString("type");

        System.out.println("EventType: " + eventType);
        
        if(EventType.INITIALIZE_EVENT.equals(eventType)) {
            boardAction.initialize();
        }

        if(EventType.MOVE_EVENT.equals(eventType)) {
            int xPos = jsonEvent.getInt("field-col");
            int yPos = jsonEvent.getInt("field-row");
            Field targetField = new Field(xPos, yPos);

            String playerId = jsonEvent.getString("playerId");
            PlayerInfo player = new PlayerInfo(playerId, "P" + playerId);
            boardAction.makeMove(targetField, player);
        }
    }
}
