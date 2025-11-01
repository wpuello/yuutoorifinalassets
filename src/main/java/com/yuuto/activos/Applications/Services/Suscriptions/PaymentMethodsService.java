package com.yuuto.activos.Applications.Services.Suscriptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuuto.activos.Domain.Entities.Suscriptions.PaymentMethods;
import com.yuuto.activos.Domain.Repositories.Suscriptions.PaymentMethodsRepository;

@Service
public class PaymentMethodsService {

    @Autowired
    private PaymentMethodsRepository paymentMethodsRepository;

     public PaymentMethods getPaymentMethodById(Long paymentMethodId) {
        if (paymentMethodId == null) {
            return null;
        }

       return paymentMethodsRepository.findById(paymentMethodId)
                .orElseThrow(() -> new RuntimeException("Payment method not found with ID: " + paymentMethodId));
    }

    
}
