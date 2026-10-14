package com.bancosol.mapper;

import com.bancosol.dto.StoreDTO;
import com.bancosol.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper extends AbstractMapper<Store, StoreDTO>{

    public StoreDTO toDTO(Store entity) {
        if (entity == null) return null;
        return new StoreDTO(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getCity(),
                entity.isActive()
        );
    }

    public Store toEntity(StoreDTO dto) {
        if (dto == null) return null;
        Store entity = new Store();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setActive(dto.isActive());
        return entity;
    }
}