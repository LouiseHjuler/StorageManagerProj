package com.unit.StorageUnitService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.List;
@Entity
@Getter
@Setter
public class StorageUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storageUnitId;
    private String storageUnitName;
    // TODO maybe add location?

}




















