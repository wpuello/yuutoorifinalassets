package com.yuuto.activos.Infraestructure.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/prueba")
@Tag(
    name = "Prueba de Api",
    description = "Este controlador se utiliza para probar el Funcionamiento de la API."
)
public class HomeController {

    @Operation(summary = "Enlace de Prueba")
    @GetMapping("")
    public String home() {
        return "La API Esta funcionando Correctamente!";
    }
    
}
