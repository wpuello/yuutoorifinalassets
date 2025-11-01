package com.yuuto.activos.Domain.Repositories.Suscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yuuto.activos.Domain.Entities.Suscriptions.Suscriptions;

public interface SuscriptionsRepository extends JpaRepository<Suscriptions, Long> {
    
}
