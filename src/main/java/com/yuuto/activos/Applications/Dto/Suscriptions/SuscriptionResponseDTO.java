package com.yuuto.activos.Applications.Dto.Suscriptions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SuscriptionResponseDTO {
    private Long id;
    private String username;
    private String planName;
    private String paymentMethod;
    private String status;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean autoRenew;
    private String authProvider;

    // Datos de la compañía
    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String companyAddress;
    private String companyNit;
}
