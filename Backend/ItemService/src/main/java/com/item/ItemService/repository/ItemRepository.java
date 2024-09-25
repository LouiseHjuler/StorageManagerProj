package com.item.ItemService.repository;

import com.item.ItemService.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // Custom method to be able to find items by storage unit id
    List<Item> findByStorageUnitId(Long storageUnitId);
}
