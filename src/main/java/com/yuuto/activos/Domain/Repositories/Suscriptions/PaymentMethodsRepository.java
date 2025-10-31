package com.yuuto.activos.Domain.Repositories.Suscriptions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuuto.activos.Domain.Entities.Suscriptions.PaymentMethods;

@Repository
public interface  PaymentMethodsRepository extends JpaRepository<PaymentMethods, Long> {
    boolean existsByName(String name);
}
