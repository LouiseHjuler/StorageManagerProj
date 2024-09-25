package com.item.ItemService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private LocalDate expiryDate;
    private Long storageUnitId; // Storing the reference to the storage unit


    // Method to check if the item has expired
    /*public boolean isExpired() {
        // Check if the expiryDate is before or equal to the current date
        return expiryDate.isBefore(LocalDate.now()) || expiryDate.isEqual(LocalDate.now());
    }*/

}


















