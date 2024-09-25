package com.unit.StorageUnitService.service;

import com.unit.StorageUnitService.dto.StorageUnitDTO;
import com.unit.StorageUnitService.model.StorageUnit;
import com.unit.StorageUnitService.repository.StorageUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageUnitService {
    @Autowired
    private StorageUnitRepository storageUnitRepository;

    // Creating a new StorageUnit
    public StorageUnitDTO createStorageUnit(StorageUnitDTO storageUnitDTO) {
        StorageUnit storageUnit = new StorageUnit();
        // Setting the storageUnit name from DTO
        storageUnit.setStorageUnitName(storageUnitDTO.getStorageUnitName());
        // Saving the entity in the repo
        storageUnit = storageUnitRepository.save(storageUnit);
        // Setting the ID back to the DTO and returning
        storageUnitDTO.setStorageUnitId(storageUnit.getStorageUnitId());
        return storageUnitDTO;
    }

    // Get all storage units
    public List<StorageUnit> getAllStorageUnits(){
        return storageUnitRepository.findAll();
    }

    // Checking if a storage unit exists by ID
    public boolean storageUnitExists(Long storageUnitId){
        // Return true if the storageUnit exists
        return storageUnitRepository.existsById(storageUnitId);
    }

    // Deleting a storage unit by the ID
    public void deleteStorageUnit(Long storageUnitId){
        storageUnitRepository.deleteById(storageUnitId);
    }
}






















