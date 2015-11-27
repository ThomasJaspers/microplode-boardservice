package de.codecentric.microplode;

import de.codecentric.microplode.messaging.MessageListener;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.codecentric.microplode.messaging.MessageListener;


@SpringBootApplication
public class PlayingFieldServiceApplication {


    public static final String queueNamePlayingField = "microplode-newgame-event-playingfield";

    @Autowired
    private MessageListener messageListener;

    @Bean
    Queue queue() {
        return new Queue(queueNamePlayingField, false);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("microplode-topic-gameservice-exchange");
    }

    @Bean
    Binding binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    public static void main(String[] args) {
        SpringApplication.run(PlayingFieldServiceApplication.class, args);
    }
}
