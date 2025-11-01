package com.yuuto.activos.Applications.Mappers;

import com.yuuto.activos.Applications.Dto.Suscriptions.SuscriptionRequestDTO;
import com.yuuto.activos.Applications.Dto.Suscriptions.SuscriptionResponseDTO;
import com.yuuto.activos.Domain.Entities.Suscriptions.Company;
import com.yuuto.activos.Domain.Entities.Suscriptions.PaymentMethods;
import com.yuuto.activos.Domain.Entities.Suscriptions.Suscriptions;
import com.yuuto.activos.Domain.Entities.Suscriptions.SuscriptionsPlans;
import com.yuuto.activos.Domain.Entities.Users.Users;

public class SuscriptionsMapper {

    public static Suscriptions toEntity(SuscriptionRequestDTO dto, Users user,
                                        SuscriptionsPlans plan,
                                        PaymentMethods payment,
                                        Company company) {
        Suscriptions s = new Suscriptions();
        s.setUser(user);
        s.setPlan(plan);
        s.setPaymentMethod(payment);
        s.setCompany(company);
        s.setExternalSubscriptionId(dto.getExternalSubscriptionId());
        s.setStartDate(dto.getStartDate() != null ? dto.getStartDate() : java.time.LocalDateTime.now());
        s.setEndDate(dto.getEndDate());
        s.setNextBillingDate(dto.getNextBillingDate());
        s.setAmount(dto.getAmount());
        s.setCurrency(dto.getCurrency() != null ? dto.getCurrency() : "USD");
        s.setAutoRenew(dto.getAutoRenew() != null ? dto.getAutoRenew() : true);
        s.setAuthProvider(user.getAuthProvider());
        s.setStatus("ACTIVE");
        return s;
    }

    public static SuscriptionResponseDTO toDTO(Suscriptions s) {
        SuscriptionResponseDTO dto = new SuscriptionResponseDTO();
        dto.setId(s.getId());
        dto.setUsername(s.getUser().getUsername());
        dto.setPlanName(s.getPlan().getName());
        dto.setPaymentMethod(s.getPaymentMethod() != null ? s.getPaymentMethod().getName() : null);
        dto.setStatus(s.getStatus());
        dto.setAmount(s.getAmount());
        dto.setCurrency(s.getCurrency());
        dto.setStartDate(s.getStartDate());
        dto.setEndDate(s.getEndDate());
        dto.setAutoRenew(s.getAutoRenew());
        dto.setAuthProvider(s.getAuthProvider());
        return dto;
    }
    
}
