package com.yuuto.activos.Infraestructure.Seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import com.yuuto.activos.Domain.Entities.Roles.Roles;
import com.yuuto.activos.Domain.Repositories.Users.RolesRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataRolesSeeder {
    @Autowired
    RolesRepository rolesRepository;

    @PostConstruct
    public void seedRoles() {

    List<String> defaultRoles = List.of("ADMIN", "USER", "MANAGER");

    for (String roleName : defaultRoles) {
            rolesRepository.findByName(roleName).ifPresentOrElse(
                existing -> {
                    // Ya existe, no hacer nada
                },
                () -> {
                    Roles role = new Roles();
                    role.setName(roleName);
                    role.setDescription("Rol predeterminado: " + roleName);
                    rolesRepository.save(role);
                    System.out.println("Rol creado: " + roleName);
                }
            );
        }
    }
}
