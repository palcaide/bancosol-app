package com.bancosol.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<E, D> {

    public abstract D toDTO(E entity);

    public abstract E toEntity(D dto);

    public List<D> toDTOList(List<E> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<E> toEntityList(List<D> dtos) {
        if (dtos == null) {
            return List.of();
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}