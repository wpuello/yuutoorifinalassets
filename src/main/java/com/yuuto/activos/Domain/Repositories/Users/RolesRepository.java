package com.yuuto.activos.Domain.Repositories.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuuto.activos.Domain.Entities.Roles.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
     Optional<Roles> findByName(String name);
}
