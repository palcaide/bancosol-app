package com.bancosol.service;

import com.bancosol.dto.CampaignDTO;
import com.bancosol.entity.Campaign;
import com.bancosol.mapper.CampaignMapper;
import com.bancosol.repository.CampaignRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    public List<CampaignDTO> listCampaigns() {
        return campaignMapper.toDTOList(this.campaignRepository.findAll());
    }

    public CampaignDTO findCampaign(Integer id) {
        if (id == null) {
            return new CampaignDTO();
        }
        Campaign campaign = this.campaignRepository.findById(id).orElseThrow();
        return campaignMapper.toDTO(campaign);
    }

    @Transactional
    public Integer saveCampaign(CampaignDTO campaignDTO) {
        return this.saveCampaign(
                campaignDTO.getId(),
                campaignDTO.getName(),
                campaignDTO.getDescription(),
                campaignDTO.getStartDate(),
                campaignDTO.getEndDate(),
                campaignDTO.getTargetKilos(),
                campaignDTO.getStatus()
        );
    }

    @Transactional
    public Integer saveCampaign(Integer id,
                                String name,
                                String description,
                                LocalDate startDate,
                                LocalDate endDate,
                                Double targetKilos,
                                String status) {

        Campaign campaign;
        if (id == null) {
            campaign = new Campaign();
        } else {
            campaign = this.campaignRepository.findById(id).orElseThrow();
        }

        campaign.setName(name);
        campaign.setDescription(description);
        campaign.setStartDate(startDate);
        campaign.setEndDate(endDate);
        campaign.setTargetKilos(targetKilos);
        campaign.setStatus(status);

        return this.campaignRepository.save(campaign).getId();
    }

    @Transactional
    public void deleteCampaign(Integer id) {
        Campaign campaign = this.campaignRepository.findById(id).orElseThrow();
        this.campaignRepository.delete(campaign);
    }
}