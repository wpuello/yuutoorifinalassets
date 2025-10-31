package com.yuuto.activos.Domain.Repositories.Suscriptions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuuto.activos.Domain.Entities.Suscriptions.SuscriptionsPlans;

@Repository
public interface SuscriptionsPlansRepository extends JpaRepository<SuscriptionsPlans, Long> {
    boolean existsByName(String name);
}
