package de.codecentric.microplode;

import de.codecentric.microplode.messaging.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;


@SpringBootApplication
public class PlayingFieldServiceApplication {


    final static String queueName = "microplode-topic";

    @Autowired
    private MessageListener messageListener;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("microplode-topic-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }


    public static void main(String[] args) {
        SpringApplication.run(PlayingFieldServiceApplication.class, args);
    }
}
