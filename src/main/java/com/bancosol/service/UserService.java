package com.bancosol.service;

import com.bancosol.dto.UserDTO;
import com.bancosol.entity.Role;
import com.bancosol.entity.User;
import com.bancosol.mapper.UserMapper;
import com.bancosol.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO findUser(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow();
        return userMapper.toDTO(user);
    }

    public List<UserDTO> listAll() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    public boolean existsUsername(String username) {
        if (username == null) return false;
        return userRepository.findAll().stream()
                .anyMatch(u -> username.equalsIgnoreCase(u.getUsername()));
    }

    public boolean existsOtherUsername(Integer currentId, String username) {
        if (username == null) return false;
        return userRepository.findAll().stream()
                .anyMatch(u -> username.equalsIgnoreCase(u.getUsername()) && !u.getId().equals(currentId));
    }

    @Transactional
    public UserDTO createUser(String username,
                              String rawPassword,
                              String email,
                              Role role,
                              Boolean enabled) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role != null ? role : Role.USER);
        user.setEnabled(enabled != null ? enabled : Boolean.TRUE);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public void updateRoleAndStatus(Integer id, Role role, Boolean enabled) {
        User user = userRepository.findById(id).orElseThrow();
        if (role != null) user.setRole(role);
        if (enabled != null) user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Transactional
    public void resetPassword(Integer id, String newRawPassword) {
        if (newRawPassword == null || newRawPassword.isBlank()) return;
        User user = userRepository.findById(id).orElseThrow();
        user.setPassword(passwordEncoder.encode(newRawPassword));
        userRepository.save(user);
    }

    @Transactional
    public void updateUserComplete(Integer id,
                                   String username,
                                   String email,
                                   Role role,
                                   Boolean enabled,
                                   String newRawPassword) {
        User user = userRepository.findById(id).orElseThrow();
        if (username != null && !username.isBlank()) user.setUsername(username.trim());
        if (email != null && !email.isBlank())       user.setEmail(email.trim());
        if (role != null)    user.setRole(role);
        if (enabled != null) user.setEnabled(enabled);
        if (newRawPassword != null && !newRawPassword.isBlank()) {
            user.setPassword(passwordEncoder.encode(newRawPassword));
        }
        userRepository.save(user);
    }

    @Transactional
    public UserDTO updateProfile(Integer id, String email) {
        User user = this.userRepository.findById(id).orElseThrow();
        user.setEmail(email);
        return userMapper.toDTO(this.userRepository.save(user));
    }

    @Transactional
    public boolean changePassword(Integer id, String currentRawPassword, String newRawPassword) {
        User user = this.userRepository.findById(id).orElseThrow();
        if (!passwordEncoder.matches(currentRawPassword, user.getPassword())) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(newRawPassword));
        this.userRepository.save(user);
        return true;
    }
}