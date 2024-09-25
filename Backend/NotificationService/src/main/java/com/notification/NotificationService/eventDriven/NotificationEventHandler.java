package com.notification.NotificationService.eventDriven;

import com.notification.NotificationService.dto.ItemDTO;
import com.notification.NotificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationEventHandler {

    private final NotificationService notificationService;

    // Listener for items with date
    @RabbitListener(queues = "${amqp.queue.date.name}")
    public void handleItemEventDate(ItemDTO itemDTO) {
        log.info("Received item with date event: {}", itemDTO);
        notificationService.handleExpiredItem(itemDTO);
    }
}