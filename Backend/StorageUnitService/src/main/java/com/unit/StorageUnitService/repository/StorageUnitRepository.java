package com.unit.StorageUnitService.repository;

import com.unit.StorageUnitService.model.StorageUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageUnitRepository extends JpaRepository<StorageUnit, Long> {
}
