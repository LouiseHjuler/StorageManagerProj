package com.item.ItemService.configuration;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmqpConfiguration {

    @Bean // for creating a TopicExhange object
    public TopicExchange itemTopicExchange(@Value("${amqp.exchange.name}") final String exchangeName){
        // Creating the TopicExchange object using the ExchangeBuilder with the given exchange name
        // The exchange is set as durable, meaning it will survive broker restarts
        return ExchangeBuilder
                .topicExchange(exchangeName)
                .durable(true)  // The durable(true) = ensures the exchange remains available after a broker restart
                .build();
    }

    // This bean will be used to convert messages to/from JSON format in the messaging system
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
