package com.healthtrack.health_api.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve estar no passado")
    private LocalDate birthDate;

    @NotNull(message = "Peso é obrigatório")
    @Positive(message = "Peso deve ser positivo")
    @DecimalMin(value = "20.0", message = "Peso mínimo é 20kg")
    @DecimalMax(value = "500.0", message = "Peso máximo é 500kg")
    private Double weight;

    @NotNull(message = "Altura é obrigatória")
    @Positive(message = "Altura deve ser positiva")
    @DecimalMin(value = "0.5", message = "Altura mínima é 0.5m")
    @DecimalMax(value = "3.0", message = "Altura máxima é 3.0m")
    private Double height;
}