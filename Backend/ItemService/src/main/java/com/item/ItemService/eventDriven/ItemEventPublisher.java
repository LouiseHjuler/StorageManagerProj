package com.item.ItemService.eventDriven;

import com.item.ItemService.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;

    public ItemEventPublisher(RabbitTemplate rabbitTemplate, @Value("${amqp.exchange.name}") String exchangeName){
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
    }

    public void publishItem(Item item){
        String routingKey = "item.date";
        log.info("Sending something to Rabbit:" );
        rabbitTemplate.convertAndSend(exchangeName, routingKey, item);
    }
}
