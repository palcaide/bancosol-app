package com.bancosol.mapper;

import com.bancosol.dto.CampaignDTO;
import com.bancosol.entity.Campaign;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {

    public CampaignDTO toDTO(Campaign entity) {
        if (entity == null) return null;
        return new CampaignDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getTargetKilos(),
                entity.getStatus()
        );
    }

    public Campaign toEntity(CampaignDTO dto) {
        if (dto == null) return null;
        Campaign entity = new Campaign();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setTargetKilos(dto.getTargetKilos());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}