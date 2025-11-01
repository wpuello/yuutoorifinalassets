package com.yuuto.activos.Applications.Dto.Suscriptions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para crear una nueva suscripción en Yuuto", example = """
{
  "externalToken": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjAxMjM0NTY3OCJ9...",  
  "planId": 1,
  "paymentMethodId": 2,
  "externalSubscriptionId": "sub_9485JKHGHJG123",
  "startDate": "2025-11-01T10:00:00",
  "endDate": "2026-11-01T10:00:00",
  "nextBillingDate": "2025-12-01T10:00:00",
  "amount": 132.000,
  "currency": "COP",
  "autoRenew": true,
  "companyName": "Tech Solutions SAS",
  "companyEmail": "contact@techsolutions.com",
  "companyPhone": "+57 312 555 8899",
  "companyAddress": "Calle 45 #22-10, Bogotá, Colombia",
  "companyNit": "900123456-7"
}
""")
public class SuscriptionRequestDTO {
    private Long planId;
    private Long paymentMethodId;
    private String externalSubscriptionId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime nextBillingDate;
    private BigDecimal amount;
    private String currency;
    private Boolean autoRenew;
    private Long companyId;

    // Datos de la compañía
    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String companyAddress;
    private String companyNit;
}
