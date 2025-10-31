package com.yuuto.activos.Domain.Entities.Suscriptions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Table(name = "payment_methods")
@Data
public class PaymentMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name; //Tarjeta de Crédito”, “Mercado Pago”, “Nequi”, “PSE

    @Column(name = "provider", nullable = false, unique = false, length = 100)
    private String provider; //MERCADO_PAGO, PAYU, STRIPE, etc

    @Column(name = "type", nullable = false, unique = false, length = 100)
    private String type; //Tipo de método (CARD, TRANSFER, WALLET, CASH, etc.).

    @Column(name = "api_key", nullable = false, unique = false)
    private String api_key; //Clave pública o token del proveedor (en producción se cifra o almacena en variables de entorno)

    @Column(name = "merchant_id", nullable = false, unique = false, length = 100)
    private String merchant_id; //Identificador del comercio en el proveedor.

    @Column(name = "external_code", nullable = false, unique = false, length = 100)
    private String external_code; //Código o ID del método según el proveedor (por ejemplo, “visa”, “master”, “pse”, etc.)

    @Column(name = "fee_percentage", nullable = false, unique = false)
    private BigDecimal fee_percentage; //Comisión que cobra el proveedor (%).

    @Column(name = "callback_url")
    private String callback_url; //URL de retorno o notificación (webhook).

    @Column(name = "logo_url")
    private String logo_url; //Logo del método de pago para mostrar en frontend.

    @Column(name = "active")
    private Boolean active; //Si está disponible para uso.

    @Column(name = "country")
    private String country; //País (ej: CO, VE, US, etc.).

    @Column(name = "supports_refunds")
    private Boolean supports_refunds; //Indica si el método admite reembolsos.

    @Column(name = "description")
    private String description; //Texto descriptivo o instrucciones para el usuario.

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void onCreate() {
    this.createdAt = LocalDateTime.now();
}

    
}
