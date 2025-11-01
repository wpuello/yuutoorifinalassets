package com.yuuto.activos.Applications.Services.Suscriptions;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yuuto.activos.Domain.Entities.Users.Users;
import com.yuuto.activos.Domain.Repositories.Users.UsersRepository;

@Service
public class UserSuscriptionsService {

    @Autowired
    private UsersRepository usersRepository;


    /**
     * Busca un usuario por email. Si no existe, lo crea automáticamente
     * con los datos del proveedor externo (Google, Facebook, etc.).
     */
    @Transactional
    public Users createOrGetExternalUser(String email, String fullName, String authProvider) {
        Optional<Users> existing = usersRepository.findByEmail(email);

        if (existing.isPresent()) {
            return existing.get();
        }

        Users newUser = new Users();
        newUser.setEmail(email);
        newUser.setFullname(fullName != null ? fullName : "External User");
        newUser.setUsername(email.split("@")[0]);
        newUser.setAuthProvider(authProvider);
        newUser.setStatus(true);
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        return usersRepository.save(newUser);
    }
    
}
