package com.yuuto.activos.Infraestructure.Seeders;

import org.springframework.boot.CommandLineRunner;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import com.yuuto.activos.Domain.Entities.Roles.Roles;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.yuuto.activos.Domain.Entities.Users.Users;
import com.yuuto.activos.Domain.Repositories.Users.RolesRepository;
import com.yuuto.activos.Domain.Repositories.Users.UsersRepository;

@Component
public class UserAdminSeeder implements CommandLineRunner {
    
  @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) {
        // Verifica si ya existe un usuario admin
        if (usersRepository.count() > 0) {
            System.out.println("Usuarios ya existentes. Seeder no ejecutado.");
            return;
        }

        // Busca el rol ADMIN (asegúrate que el seeder de Roles ya haya corrido antes)
        Roles adminRole = rolesRepository.findByName("ADMIN")
                .orElseGet(() -> {
                    Roles role = new Roles();
                    role.setName("ADMIN");
                    role.setDescription("Rol predeterminado: ADMIN");
                    return rolesRepository.save(role);
                });

        // Crea el usuario admin
        Users admin = new Users();
        admin.setUsername("admin");
        admin.setEmail("admin@yuuto.com.co");
        admin.setAddress("Cartagena - Colombia");
        admin.setFullname("Administrador del Sistema");
        admin.setPassword(passwordEncoder.encode("Elles2012*.*")); //Contraseña encriptada
        admin.setRole(adminRole);
        admin.setStatus(true);
        admin.setPhoneNumber("6639854545");
        admin.setAuthProvider("LOCAL");
        admin.setCreatedBy("Seeder");
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());

        usersRepository.save(admin);

        System.out.println("Seeder ejecutado: Usuario administrador creado correctamente.");
    }
}