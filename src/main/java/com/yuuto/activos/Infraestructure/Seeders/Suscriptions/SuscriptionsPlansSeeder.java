package com.yuuto.activos.Infraestructure.Seeders.Suscriptions;
import org.springframework.stereotype.Component;
import com.yuuto.activos.Domain.Entities.Suscriptions.SuscriptionsPlans;
import com.yuuto.activos.Domain.Repositories.Suscriptions.SuscriptionsPlansRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;

@Component
public class SuscriptionsPlansSeeder implements CommandLineRunner {
    
    @Autowired
    SuscriptionsPlansRepository plansRepository;


    @Override
    public void run(String... args) {
        if (plansRepository.count() > 0) {
            System.out.println("Los planes de suscripción ya existen. Seeder no ejecutado.");
            return;
        }

        List<SuscriptionsPlans> plans = List.of(
            createPlan("Básico Mensual", BigDecimal.valueOf(30000.00), "COP", 30, true, 
                "Ideal para pequeños negocios o emprendedores. Acceso básico al sistema.", 2),
            createPlan("Profesional Mensual", BigDecimal.valueOf(60000.0), "COP", 30, true, 
                "Incluye soporte prioritario y reportes avanzados.", 10),
            createPlan("Empresarial Anual", BigDecimal.valueOf(600000.0), "COP", 365, true, 
                "Diseñado para empresas grandes. Incluye acceso multiusuario y mantenimiento preferente.", 100),
            createPlan("Demo Gratuito", BigDecimal.valueOf(0.0), "COP", 7, true, 
                "Plan gratuito de prueba por 7 días con acceso limitado.", 1)
        );

        plansRepository.saveAll(plans);
        System.out.println("Seeder ejecutado: planes de suscripción inicializados con espacio en GB.");
    }

    private SuscriptionsPlans createPlan(String name, BigDecimal price, String currency, Integer durationDays,
                                         Boolean isActive, String description, Integer storageLimitGb) {
        SuscriptionsPlans plan = new SuscriptionsPlans();
        plan.setName(name);
        plan.setPrice(price);
        plan.setCurrency(currency);
        plan.setDurationDays(durationDays);
        plan.setIsActive(isActive);
        plan.setDescription(description);
        plan.setStorageLimitGb(storageLimitGb);
        plan.setCreatedAt(LocalDateTime.now());
        return plan;
    }
    
}