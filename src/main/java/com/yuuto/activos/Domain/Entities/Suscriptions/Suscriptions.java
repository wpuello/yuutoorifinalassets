package com.yuuto.activos.Domain.Entities.Suscriptions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.yuuto.activos.Domain.Entities.Tenants.Tenants;
import com.yuuto.activos.Domain.Entities.Users.Users;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "suscriptions")
@Data
public class Suscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private SuscriptionsPlans plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethods paymentMethod;

    //ID del proveedor externo (MercadoPago, Stripe, etc.)
    @Column(name = "external_subscription_id", length = 150)
    private String externalSubscriptionId;

     // Método de autenticación (LOCAL, GOOGLE, FACEBOOK)
    @Column(name = "auth_provider", length = 50, nullable = false)
    private String authProvider = "LOCAL";


    // Estado actual de la suscripción
    @Column(name = "status", length = 20, nullable = false)
    private String status = "PENDING";

    // Fechas de inicio, fin y próxima facturación
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "next_billing_date")
    private LocalDateTime nextBillingDate;

    // Monto 
    @Column(nullable = false)
    private BigDecimal amount;

    //Moneda
    @Column(length = 10, nullable = false)
    private String currency = "USD";

    //Renovación automática
    @Column(name = "auto_renew", nullable = false)
    private Boolean autoRenew = true;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToOne(mappedBy = "subscription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Tenants tenant;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    
}
