package com.bancosol.mapper;

import com.bancosol.dto.DonationDTO;
import com.bancosol.entity.Campaign;
import com.bancosol.entity.Donation;
import com.bancosol.entity.Store;
import com.bancosol.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DonationMapper {

    public DonationDTO toDTO(Donation entity) {
        if (entity == null) return null;
        return new DonationDTO(
                entity.getId(),
                entity.getFoodCategory(),
                entity.getQuantityKilos(),
                entity.getDonationDate(),
                entity.getStatus(),
                entity.getCampaign() != null ? entity.getCampaign().getId() : null,
                entity.getCampaign() != null ? entity.getCampaign().getName() : null,
                entity.getStore() != null ? entity.getStore().getId() : null,
                entity.getStore() != null ? entity.getStore().getName() : null,
                entity.getUser() != null ? entity.getUser().getId() : null,
                entity.getUser() != null ? entity.getUser().getUsername() : null
        );
    }

    public Donation toEntity(DonationDTO dto, Campaign campaign, Store store, User user) {
        if (dto == null) return null;
        Donation entity = new Donation();
        entity.setId(dto.getId());
        entity.setFoodCategory(dto.getFoodCategory());
        entity.setQuantityKilos(dto.getQuantityKilos());
        entity.setDonationDate(dto.getDonationDate());
        entity.setStatus(dto.getStatus());
        entity.setCampaign(campaign);
        entity.setStore(store);
        entity.setUser(user);
        return entity;
    }
}