package com.bancosol.service;

import com.bancosol.dto.StoreDTO;
import com.bancosol.entity.Store;
import com.bancosol.mapper.StoreMapper;
import com.bancosol.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreService{

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public List<StoreDTO> listStores() {
        List<Store> stores = this.storeRepository.findAll();
        return storeMapper.toDTOList(stores);
    }

    public StoreDTO findStore(Integer id) {
        if (id == null) {
            return new StoreDTO();
        }
        Store store = this.storeRepository.findById(id).orElseThrow();
        return storeMapper.toDTO(store);
    }

    @Transactional
    public Integer saveStore(StoreDTO storeDTO) {
        return this.saveStore(
                storeDTO.getId(),
                storeDTO.getName(),
                storeDTO.getAddress(),
                storeDTO.getCity(),
                storeDTO.isActive()
        );
    }

    @Transactional
    public Integer saveStore(Integer id,
                             String name,
                             String address,
                             String city,
                             boolean active) {

        Store store;
        if (id == null) {
            store = new Store();
        } else {
            store = this.storeRepository.findById(id).orElseThrow();
        }

        store.setName(name);
        store.setAddress(address);
        store.setCity(city);
        store.setActive(active);

        return this.storeRepository.save(store).getId();
    }

    @Transactional
    public void deleteStore(Integer id) {
        Store store = this.storeRepository.findById(id).orElseThrow();
        this.storeRepository.delete(store);
    }
}