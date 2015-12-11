package de.codecentric.microplode.messaging;

import de.codecentric.microplode.action.BoardAction;
import de.codecentric.microplode.configuration.Queues;
import de.codecentric.microplode.messaging.api.Event;
import de.codecentric.microplode.messaging.api.EventType;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EventResultHandler {

    @Autowired
    private BoardAction boardAction;
    
    @RabbitListener(queues=Queues.GAMESERVICE_BOARDSERVICE)
    public void handleMessage(@Payload Event event) {
        System.out.println("Event received");
        System.out.println("EventType: " + event.getType().getText());
        
        if(EventType.INITIALIZE_EVENT.equals(event.getType())) {
            boardAction.initialize();
        }
    }
}
