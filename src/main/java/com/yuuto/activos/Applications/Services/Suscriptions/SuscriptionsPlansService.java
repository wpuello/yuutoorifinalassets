package com.yuuto.activos.Applications.Services.Suscriptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuuto.activos.Domain.Entities.Suscriptions.SuscriptionsPlans;
import com.yuuto.activos.Domain.Repositories.Suscriptions.SuscriptionsPlansRepository;

@Service
public class SuscriptionsPlansService {
    
  @Autowired
    private SuscriptionsPlansRepository plansRepository;

    /**
     * Busca un plan por su ID y lanza excepción si no existe.
     */
    public SuscriptionsPlans getPlanById(Long planId) {
        return plansRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found with ID: " + planId));
    }
}
