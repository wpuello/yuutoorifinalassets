package com.yuuto.activos.Applications.Dto.Users;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(
    name = "Users Login DTO",
    description = "Representa los campos para poder Autenticarse."
)
public class UserLoginRequestDTO {


    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Schema(description = "Username", example = "ghtuty")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Schema(description = "Contraseña que será encriptada", example = "ghtuty1121212")
    private String password;
}
