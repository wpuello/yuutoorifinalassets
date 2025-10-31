package com.yuuto.activos.Domain.Entities.Users;

import java.time.LocalDateTime;
import com.yuuto.activos.Domain.Entities.Roles.Roles;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "users")
@Data
@Schema(
    name = "Users",
    description = "Representa un usuario en el sistema de yuuto."
)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "full_name", length = 150)
    private String fullname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Roles role;

    @Column(name = "status")
    private boolean status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;   

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "phoneNumber", length = 50)
    private String phoneNumber;

    @Column(name = "address", length = 255)
    private String address;

    // Campo para identificar el método de autenticación
    @Column(name = "auth_provider", length = 50, nullable = false)
    private String authProvider = "LOCAL"; //LOCAL, FACEBOOK, GMAIL

    @Column(name = "createdBy", length = 50)
    private String createdBy;

    @Column(name = "updatedBy", length = 50)
    private String updatedBy;

}
