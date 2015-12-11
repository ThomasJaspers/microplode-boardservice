package de.codecentric.microplode;

import de.codecentric.microplode.configuration.MessagingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("de.codecentric.microplode")
public class BoardServiceApplication {

    @Autowired
    MessagingConfiguration messagingConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(BoardServiceApplication.class, args);
    }
}
