package com.item.ItemService.service;

import com.item.ItemService.dto.ItemDTO;
import com.item.ItemService.eventDriven.ItemEventPublisher;
import com.item.ItemService.model.Item;
import com.item.ItemService.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    private final ItemEventPublisher itemEventPublisher; // For event publishing
    private final String storageUnitServiceUrl;
    private final RestTemplate restTemplate; // needed for inter-service communcation


    // @Value is saying that "I am getting the url from here..."
    public ItemService(RestTemplateBuilder restTemplateBuilder, @Value("http://localhost:8081") String url, ItemEventPublisher itemEventPublisher){
        // restTemplate is a library that lets me call another service
        this.restTemplate = restTemplateBuilder.build();
        this.storageUnitServiceUrl = url;
        this.itemEventPublisher = itemEventPublisher;
    }

    // Add an Item to a specific storage unit
    public ItemDTO addItemToStorageUnit(ItemDTO itemDTO) {
        try {
            log.info("Checking if storage unit with ID exists: " + itemDTO.getStorageUnitId());

            // Need to check first if the storage unit exist by calling StorageUnit service
            ResponseEntity<Void> response = restTemplate.getForEntity(
                    storageUnitServiceUrl + "/storageUnits/" + itemDTO.getStorageUnitId(), Void.class);


            if (response.getStatusCode() != HttpStatus.OK){
                throw new RuntimeException("Storage unit is not found");
            }

            // Converting Dto to entity and saving the item
            Item item = new Item();
            item.setItemName(itemDTO.getItemName());
            item.setStorageUnitId(itemDTO.getStorageUnitId());
            item.setItemDescription(itemDTO.getItemDescription());
            item.setExpiryDate(itemDTO.getItemDate());
            item = itemRepository.save(item);

            log.warn("EXPIRY DATE: " + item.getExpiryDate());
            // This is related to event handling
            if (item.getExpiryDate() != null) {
                log.info("Item has expiry date, publishing event for notification");
                // Async event publishing
                itemEventPublisher.publishItem(item);
            }
            // Converting entity back to Dto
            itemDTO.setItemId((item.getItemId()));
            return itemDTO;
        } catch (Exception e){
            log.error("Error adding item to storage unit", e);
            throw new RuntimeException("Error adding item to storage unit", e);
        }
    }

    // Get all items regardless of Storage uniit
    public List<Item> getAllItems() {
        return itemRepository.findAll(); // Retrieves all items from the database
    }

    // Get one Item by Id
    public Optional<Item> getItemById(Long itemId){
        return itemRepository.findById(itemId);
    }

    // Get all the items in a specific storage unit
    public List<Item> getItemsInStorage(Long storageUnitId){
        return itemRepository.findByStorageUnitId(storageUnitId);
    }

    // Update an existing item
    public Item updateItem(Item item){
        // Checking if the item exists
        Optional<Item> existingItem = itemRepository.findById(item.getItemId());

        if (existingItem.isPresent()){
            // Saving the updated item to the repoo
            return itemRepository.save(item);
        } else {
            throw new RuntimeException("Item nnot found");
        }
    }
    // Delete an item
    public void deleteItem(Long itemId){
        Optional<Item> item = itemRepository.findById(itemId);

        if (item.isPresent()) {
            itemRepository.deleteById(itemId);
        } else {
            throw new RuntimeException("Item not found");
        }
    }

}


















