package com.bancosol.service;

import com.bancosol.dto.CampaignDTO;
import com.bancosol.entity.Campaign;
import com.bancosol.mapper.CampaignMapper;
import com.bancosol.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;

    public CampaignService(CampaignRepository campaignRepository, CampaignMapper campaignMapper) {
        this.campaignRepository = campaignRepository;
        this.campaignMapper = campaignMapper;
    }

    @Transactional(readOnly = true)
    public List<CampaignDTO> findAll() {
        return campaignRepository.findAll()
                .stream()
                .map(campaignMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CampaignDTO findById(Integer id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Campaña no encontrada con ID: " + id));
        return campaignMapper.toDTO(campaign);
    }

    @Transactional
    public CampaignDTO save(CampaignDTO dto) {
        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio");
        }
        Campaign entity = campaignMapper.toEntity(dto);
        Campaign saved = campaignRepository.save(entity);
        return campaignMapper.toDTO(saved);
    }

    @Transactional
    public void deleteById(Integer id) {
        campaignRepository.deleteById(id);
    }
}