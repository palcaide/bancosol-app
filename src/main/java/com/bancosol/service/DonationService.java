package com.bancosol.service;

import com.bancosol.dto.DonationDTO;
import com.bancosol.entity.Campaign;
import com.bancosol.entity.Donation;
import com.bancosol.entity.Store;
import com.bancosol.entity.User;
import com.bancosol.mapper.DonationMapper;
import com.bancosol.repository.CampaignRepository;
import com.bancosol.repository.DonationRepository;
import com.bancosol.repository.StoreRepository;
import com.bancosol.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final CampaignRepository campaignRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final DonationMapper donationMapper;

    public DonationService(DonationRepository donationRepository,
                           CampaignRepository campaignRepository,
                           StoreRepository storeRepository,
                           UserRepository userRepository,
                           DonationMapper donationMapper) {
        this.donationRepository = donationRepository;
        this.campaignRepository = campaignRepository;
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
        this.donationMapper = donationMapper;
    }

    @Transactional(readOnly = true)
    public List<DonationDTO> findAll() {
        return donationRepository.findAll()
                .stream()
                .map(donationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DonationDTO createDonation(DonationDTO dto) {
        Campaign campaign = campaignRepository.findById(dto.getCampaignId())
                .orElseThrow(() -> new IllegalArgumentException("Campaña no encontrada"));
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("Punto de recogida no encontrado"));

        User user = null;
        if (dto.getUserId() != null) {
            user = userRepository.findById(dto.getUserId()).orElse(null);
        }

        // Estado inicial obligatorio en el flujo
        dto.setStatus("PENDING");

        Donation entity = donationMapper.toEntity(dto, campaign, store, user);
        Donation saved = donationRepository.save(entity);
        return donationMapper.toDTO(saved);
    }

    @Transactional
    public DonationDTO verifyDonation(Integer id) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Donación no encontrada con ID: " + id));
        donation.setStatus("VERIFIED");
        Donation updated = donationRepository.save(donation);
        return donationMapper.toDTO(updated);
    }
}