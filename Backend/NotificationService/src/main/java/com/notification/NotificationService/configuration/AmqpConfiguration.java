package com.notification.NotificationService.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AmqpConfiguration {

    // exchanges
    @Bean
    public TopicExchange itemTopicExchange(@Value("${amqp.exchange.name}") final String exchangeName){
        return ExchangeBuilder
                .topicExchange(exchangeName)
                .durable(true)
                .build();
    }

    // Binding for items with expiry date
    @Bean
    public Binding expiryItemBinding(final Queue expiryItemQueue, final TopicExchange itemTopicExchange){
        return BindingBuilder
                .bind(expiryItemQueue)
                .to(itemTopicExchange)
                .with("item.date");
    }

    // This queue will hold the item events related to expiry dates
    @Bean
    public Queue expiryItemQueue(@Value("${amqp.queue.date.name}") final String queueName){
        return QueueBuilder
                .durable(queueName)
                .build();
    }

    // JSON converter for message handling
    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory(){
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        MappingJackson2MessageConverter jsonConverter = new MappingJackson2MessageConverter();
        jsonConverter.getObjectMapper().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        factory.setMessageConverter(jsonConverter);
        return factory;
    }

    //
    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(final MessageHandlerMethodFactory messageHandlerMethodFactory){
        return (c) -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }
}