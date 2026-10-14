package com.bancosol.service;

import com.bancosol.dto.UserDTO;
import com.bancosol.entity.User;
import com.bancosol.mapper.UserMapper;
import com.bancosol.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO registerUser(UserDTO dto) {
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña es requerida para el registro");
        }
        User entity = userMapper.toEntity(dto);
        // Cifrado obligatorio de la contraseña antes de guardar en PostgreSQL
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Rol por defecto si no viene indicado
        if (entity.getRole() == null || entity.getRole().isBlank()) {
            entity.setRole("ROLE_USER");
        }
        entity.setEnabled(true);

        User saved = userRepository.save(entity);
        return userMapper.toDTO(saved);
    }

    @Transactional
    public void updateUserRole(Integer id, String newRole) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
        user.setRole(newRole);
        userRepository.save(user);
    }

    @Transactional
    public void toggleUserStatus(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }
}