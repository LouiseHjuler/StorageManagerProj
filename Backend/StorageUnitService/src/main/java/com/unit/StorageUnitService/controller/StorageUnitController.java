package com.unit.StorageUnitService.controller;

import com.unit.StorageUnitService.dto.StorageUnitDTO;
import com.unit.StorageUnitService.model.StorageUnit;
import com.unit.StorageUnitService.service.StorageUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/storageUnits")
public class StorageUnitController {

    @Autowired
    private StorageUnitService storageUnitService;

    @GetMapping
    public ResponseEntity<List<StorageUnit>> getAllStorageUnits(){
        return ResponseEntity.ok(storageUnitService.getAllStorageUnits());
    }


    @GetMapping("/{storageUnitId}")
    public ResponseEntity<Boolean> checkStorageUnitExists(@PathVariable Long storageUnitId){

        try {
           boolean exists = storageUnitService.storageUnitExists(storageUnitId);
           return ResponseEntity.ok(exists);
       } catch (Exception e){
           log.error("Error checking if storage unit exists", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
       }
    }

    @PostMapping
    public ResponseEntity<StorageUnitDTO> createStorageUnit(@RequestBody StorageUnitDTO storageUnitDTO){
        StorageUnitDTO createdStorageUnit = storageUnitService.createStorageUnit(storageUnitDTO);
        return ResponseEntity.ok(createdStorageUnit);
    }
}




















