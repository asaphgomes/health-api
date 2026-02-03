package com.healthtrack.health_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100)
    private String name;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Data de nascimento é obrigatória")
    private LocalDate birthDate;

    @NotNull(message = "Peso é obrigatório")
    @Positive(message = "Peso deve ser positivo")
    private Double weight; // em kg

    @NotNull(message = "Altura é obrigatória")
    @Positive(message = "Altura deve ser positiva")
    private Double height; // em metros

    private Double imc;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<WeightHistory> weightHistory;
}