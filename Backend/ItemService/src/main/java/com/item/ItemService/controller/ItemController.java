package com.item.ItemService.controller;

import com.item.ItemService.dto.ItemDTO;
import com.item.ItemService.model.Item;
import com.item.ItemService.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping
    public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO itemDTO){
        ItemDTO createdItem = itemService.addItemToStorageUnit(itemDTO);
        return ResponseEntity.ok(createdItem);
    }


    // Getting one item from a specific storage unit
    @GetMapping("/storage-unit/{storageUnitId}")
    public ResponseEntity<List<Item>> getItemsInStorageUnit(@PathVariable Long storageUnitId){
        return ResponseEntity.ok(itemService.getItemsInStorage(storageUnitId));
    }

    // Get all items
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){
        return ResponseEntity.ok(itemService.getAllItems());
    }

    // Get an item by Id
    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById (@PathVariable Long itemId){

        Optional<Item> item = itemService.getItemById(itemId);
        if(item.isPresent()){
            return new ResponseEntity<>(item.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // Updating an existing item
    @PutMapping ("/{itemId}")
    ResponseEntity<Item> updateItem(@PathVariable Long itemId, @RequestBody Item item){

        Optional<Item> existingItem = itemService.getItemById(itemId);
        if (existingItem.isPresent()){
            // Setting the Id
            item.setItemId(itemId);
            Item updatedItem = itemService.updateItem(item);
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // DEleting an item by its id
    @DeleteMapping("{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId){
        if (itemService.getItemById(itemId).isPresent()){
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Successful deletion
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}






















