package de.codecentric.microplode.messaging;

import de.codecentric.microplode.action.BoardAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageReceiver {

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private BoardAction boardAction;

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        boardAction.newGame();
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
