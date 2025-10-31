package com.yuuto.activos.Domain.Entities.Suscriptions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "suscriptions_plans")
@Data
public class SuscriptionsPlans {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String currency = "COP";

    @Column(name = "duration_days", nullable = false)
    private Integer durationDays; // ej: 30 para mensual, 365 para anual

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    // Espacio disponible en GB //Para Subida de Documentos
    @Column(name = "storage_limit_gb", nullable = false)
    private Integer storageLimitGb = 1; // valor por defecto, 1 GB

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    

}
