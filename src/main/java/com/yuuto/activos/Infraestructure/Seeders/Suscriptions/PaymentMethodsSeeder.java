package com.yuuto.activos.Infraestructure.Seeders.Suscriptions;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.yuuto.activos.Domain.Entities.Suscriptions.PaymentMethods;
import com.yuuto.activos.Domain.Repositories.Suscriptions.PaymentMethodsRepository;

@Component
public class PaymentMethodsSeeder implements CommandLineRunner {
    
    @Autowired
    PaymentMethodsRepository paymentMethodsRepository;


    @Override
    public void run(String... args) {
        if (paymentMethodsRepository.count() > 0) {
            System.out.println("Métodos de pago ya existen. Seeder no ejecutado.");
            return;
        }

        List<PaymentMethods> methods = List.of(
            createMethod("Tarjeta de Crédito", "MERCADO_PAGO", "CARD", "MP-API-123", "MERCADO001", "visa", new BigDecimal("2.50"), "https://api.mercadopago.com/webhook", "https://cdn.icons/visa.png", true, "CO", true, "Pago con tarjetas Visa o Mastercard"),
            createMethod("Nequi", "MERCADO_PAGO", "WALLET", "MP-API-123", "MERCADO002", "nequi", new BigDecimal("1.00"), "https://api.mercadopago.com/webhook", "https://cdn.icons/nequi.png", true, "CO", true, "Pago con billetera Nequi"),
            createMethod("PSE", "MERCADO_PAGO", "TRANSFER", "MP-API-123", "MERCADO003", "pse", new BigDecimal("1.50"), "https://api.mercadopago.com/webhook", "https://cdn.icons/pse.png", true, "CO", true, "Transferencia bancaria PSE"),
            createMethod("Efecty", "MERCADO_PAGO", "CASH", "MP-API-123", "MERCADO004", "efecty", new BigDecimal("2.00"), "https://api.mercadopago.com/webhook", "https://cdn.icons/efecty.png", true, "CO", false, "Pago en efectivo en puntos Efecty")
        );

        paymentMethodsRepository.saveAll(methods);
        System.out.println("Seeder ejecutado: métodos de pago inicializados.");
    }

    private PaymentMethods createMethod(
            String name, String provider, String type, String apiKey, String merchantId,
            String externalCode, BigDecimal fee, String callbackUrl, String logoUrl,
            boolean active, String country, Boolean supportsRefunds, String description) {

        PaymentMethods method = new PaymentMethods();
        method.setName(name);
        method.setProvider(provider);
        method.setType(type);
        method.setApi_key(apiKey);
        method.setMerchant_id(merchantId);
        method.setExternal_code(externalCode);
        method.setFee_percentage(fee);
        method.setCallback_url(callbackUrl);
        method.setLogo_url(logoUrl);
        method.setActive(active);
        method.setCountry(country);
        method.setSupports_refunds(supportsRefunds);
        method.setDescription(description);
        return method;
    }
}
