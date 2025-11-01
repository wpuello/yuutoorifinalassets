package com.yuuto.activos.Infraestructure.Controllers.Suscriptions;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.yuuto.activos.Applications.Dto.Suscriptions.SuscriptionRequestDTO;
import com.yuuto.activos.Applications.Dto.Suscriptions.SuscriptionResponseDTO;
import com.yuuto.activos.Applications.Services.Suscriptions.SuscriptionsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/subscriptions")
@Tag(
    name = "Creacion de Suscripciones Iniciales",
    description = "Este controlador se utiliza para crear una suscripcion principal con un usuario proveedor externo."
)
public class SuscriptionsController {

    @Autowired
    private SuscriptionsService suscriptionsService;

    @Operation(summary = "Crear Suscripcion")
    @PostMapping
    public ResponseEntity<SuscriptionResponseDTO> createSubscription(
            @RequestBody SuscriptionRequestDTO request,
            @RequestHeader(value = "Authorization", required = false) String externalToken) {

        if (externalToken != null && externalToken.startsWith("Bearer ")) {
            String googleToken = externalToken.substring(7);
            // Aquí podrías validar el token de Google con la librería oficial si lo deseas
            System.out.println("Token de Google recibido: " + googleToken);
        } else {
            System.out.println("Creando suscripción sin autenticación externa...");
        }

       SuscriptionResponseDTO response = suscriptionsService.createSubscription(request, externalToken);
        return ResponseEntity.ok(response);
    }
    
}
