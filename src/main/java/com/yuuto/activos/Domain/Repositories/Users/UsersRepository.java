package com.yuuto.activos.Domain.Repositories.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuuto.activos.Domain.Entities.Users.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
     Optional<Users> findByUsername(String username);
     Optional<Users> findByEmail(String email);
}
