package com.yuuto.activos.Infraestructure.Controllers.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yuuto.activos.Applications.Dto.Users.UserLoginRequestDTO;
import com.yuuto.activos.Infraestructure.Security.JwtTokenProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
@Tag(
    name = "Autenticación Local",
    description = "Este controlador se utiliza para la Autenticación y prueba de URLs."
)
public class AuthController {

    @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired 
    private JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "Obtener Autenticación")
    @PostMapping("/login")
    public ResponseEntity<?> mainlogin(@Valid @RequestBody UserLoginRequestDTO req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );

            String token = jwtTokenProvider.generateToken(req.getUsername());
            return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("{\"error\":\"Credenciales inválidas\"}");
        }

        /*{
            "username": "destroyer",
            "password": "123456"
        } 
        */
    }
    
}
