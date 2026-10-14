package com.bancosol.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role; // ADMIN, MANAGER, USER

    @Column(nullable = false)
    private boolean enabled = true;

    // Campos para el reseteo autoservicio de contraseña
    private String resetToken;
    private LocalDateTime resetTokenExpiry;
}