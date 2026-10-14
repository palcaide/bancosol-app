package com.bancosol.mapper;

import com.bancosol.dto.UserDTO;
import com.bancosol.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User entity) {
        if (entity == null) return null;
        return new UserDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                null, // Nunca exponemos la contraseña en el DTO
                entity.getRole(),
                entity.isEnabled()
        );
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) return null;
        User entity = new User();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setEnabled(dto.isEnabled());
        return entity;
    }
}