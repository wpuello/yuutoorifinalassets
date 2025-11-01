package com.yuuto.activos.Applications.Services.Suscriptions;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.yuuto.activos.Applications.Dto.Suscriptions.SuscriptionRequestDTO;
import com.yuuto.activos.Applications.Dto.Suscriptions.SuscriptionResponseDTO;
import com.yuuto.activos.Applications.Mappers.SuscriptionsMapper;
import com.yuuto.activos.Domain.Entities.Suscriptions.Company;
import com.yuuto.activos.Domain.Entities.Suscriptions.PaymentMethods;
import com.yuuto.activos.Domain.Entities.Suscriptions.Suscriptions;
import com.yuuto.activos.Domain.Entities.Suscriptions.SuscriptionsPlans;
import com.yuuto.activos.Domain.Entities.Users.Users;
import com.yuuto.activos.Domain.Repositories.Suscriptions.SuscriptionsRepository;


@Service
public class SuscriptionsService {

    @Autowired
    private SuscriptionsRepository suscriptionsRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserSuscriptionsService userSuscriptionsService;

    @Autowired
    private SuscriptionsPlansService planService;

    @Autowired
    private PaymentMethodsService paymentService;

    private static final String GOOGLE_CLIENT_ID = "TU_CLIENT_ID_DE_GOOGLE"; // reemplázalo por el real

    @Transactional
    public SuscriptionResponseDTO createSubscription(SuscriptionRequestDTO dto, String externalToken) {

        String email = null;
        String fullName = null;
        String authProvider = "none";

        // Si llega un token de Google, intentamos validarlo
        if (externalToken != null && !externalToken.isEmpty()) {
            if (externalToken.startsWith("Bearer ")) {
                externalToken = externalToken.substring(7);
            }

            GoogleIdToken idToken = verifyGoogleToken(externalToken);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                email = payload.getEmail();
                fullName = (String) payload.get("name");
                authProvider = "google";
            }
        }

        // Crear o buscar usuario externo
        Users user = userSuscriptionsService.createOrGetExternalUser(email, fullName, authProvider);

        // Obtener plan y método de pago
        SuscriptionsPlans plan = planService.getPlanById(dto.getPlanId());
        PaymentMethods payment = paymentService.getPaymentMethodById(dto.getPaymentMethodId());

        // Crear o recuperar compañía
        Company company = companyService.createOrGetCompany(dto);

        // Crear suscripción
        Suscriptions entity = SuscriptionsMapper.toEntity(dto, user, plan, payment, company);
        Suscriptions saved = suscriptionsRepository.save(entity);

        return SuscriptionsMapper.toDTO(saved);
    }

    private GoogleIdToken verifyGoogleToken(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), new GsonFactory()) // o JacksonFactory si ya tienes la dependencia
                    .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                    .build();

            // Este método devuelve el objeto GoogleIdToken o null si no es válido
            return verifier.verify(idTokenString);

        } catch (Exception e) {
            System.out.println("Error verificando token de Google: " + e.getMessage());
            return null;
        }
    }

}
