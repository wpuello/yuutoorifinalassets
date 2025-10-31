package com.yuuto.activos.Infraestructure.Configurations;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import java.util.Base64;

@Component
public class SwaggerAuthFilter extends OncePerRequestFilter {

    @Value("${swagger.auth.username}")
    private String USERNAME;

    @Value("${swagger.auth.password}")
    private String PASSWORD;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Solo proteger Swagger
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")) {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Basic ")) {
                response.setHeader("WWW-Authenticate", "Basic realm=\"Swagger UI\"");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized access to Swagger UI");
                return;
            }

            String base64Credentials = authHeader.substring("Basic ".length());
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            String[] values = credentials.split(":", 2);

            if (!USERNAME.equals(values[0]) || !PASSWORD.equals(values[1])) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid credentials for Swagger UI");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}