package com.notification.NotificationService.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemDTO {
    private Long itemId;
    private String itemName;
    private String itemLocation;
    private LocalDate itemDate;
    private Long storageUnitId;
}


