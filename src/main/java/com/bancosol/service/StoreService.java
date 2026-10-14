package com.bancosol.service;

import com.bancosol.dto.StoreDTO;
import com.bancosol.entity.Store;
import com.bancosol.mapper.StoreMapper;
import com.bancosol.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Transactional(readOnly = true)
    public List<StoreDTO> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(storeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StoreDTO findById(Integer id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Punto de recogida no encontrado con ID: " + id));
        return storeMapper.toDTO(store);
    }

    @Transactional
    public StoreDTO save(StoreDTO dto) {
        Store entity = storeMapper.toEntity(dto);
        Store saved = storeRepository.save(entity);
        return storeMapper.toDTO(saved);
    }

    @Transactional
    public void deleteById(Integer id) {
        storeRepository.deleteById(id);
    }
}