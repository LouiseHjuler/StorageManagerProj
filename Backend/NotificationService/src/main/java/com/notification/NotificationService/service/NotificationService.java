package com.notification.NotificationService.service;

import com.notification.NotificationService.dto.ItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

   // private final EmailService emailService;

    /*@Autowired
    public NotificationService(EmailService emailService){
        this.emailService = emailService;
    }*/

    // Handling expired items
    public void handleExpiredItem(ItemDTO itemDTO) {
        log.info("Handling expired item for notification: {}", itemDTO);
        // TODO Here I have to call the emailService
        //emailService.sendExpiryNotifification(itemEvent);
    }

    // Handling valid items
    public void handleValidItem(ItemDTO itemDTO){
        log.info("Handling valid item for notification: {}", itemDTO);
    }

}
