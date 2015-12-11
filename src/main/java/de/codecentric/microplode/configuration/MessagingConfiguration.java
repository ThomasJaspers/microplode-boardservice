package de.codecentric.microplode.configuration;

import de.codecentric.microplode.messaging.EventResultHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableRabbit
public class MessagingConfiguration implements RabbitListenerConfigurer {

    /*
     * Outbound Configuration
     */    
    @Bean
    public Queue queueToBoardService() {
        return new Queue(Queues.BOARDSERVICE_GAMESERVICE, false);
    }

    @Bean
    public Queue queueToPresentationService() {
        return new Queue(Queues.BOARDSERVICE_PRESENTATIONSERVICE, false);
    }

    @Bean
    public Queue queueFromGameService() {
        return new Queue(Queues.GAMESERVICE_BOARDSERVICE, false);
    }


    @Bean
    public FanoutExchange exchangeToBoardService() {
        return new FanoutExchange(Queues.BOARDSERVICE_GAMESERVICE_EXCHANGE);
    }

    @Bean
    public FanoutExchange exchangeToPresentationService() {
        return new FanoutExchange(Queues.BOARDSERVICE_PRESENTATIONSERVICE_EXCHANGE);
    }

    @Bean
    public FanoutExchange exchangeFromGameService() {
        return new FanoutExchange(Queues.GAMESERVICE_BOARDSERVICE_EXCHANGE);
    }

    @Bean
    public List<Binding> binding() {
        return Arrays.asList(
                BindingBuilder.bind(queueFromGameService()).to(exchangeFromGameService()),
                BindingBuilder.bind(queueToBoardService()).to(exchangeToBoardService()),
                BindingBuilder.bind(queueToPresentationService()).to(exchangeToPresentationService()));
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /*
     * Inbound Configuration
     * We are using Annotation-Driven-Message-Listening, described here
     * http://docs
     * .spring.io/spring-amqp/reference/htmlsingle/#async-annotation-driven
     */    
    @Autowired
    public ConnectionFactory connectionFactory;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }

    @Bean
    public EventResultHandler eventResultHandler() {
        return new EventResultHandler();
    }

    @Override
    public void configureRabbitListeners(
            RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }

    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }
}
