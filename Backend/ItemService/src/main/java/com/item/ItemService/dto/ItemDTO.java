package com.item.ItemService.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ItemDTO {

    private Long itemId; // TODO REmove or not?
    private String itemName;
    private String itemDescription;
    private LocalDate itemDate;
    // Id of the storage unit to which the item belongs to
    private Long storageUnitId;
}
