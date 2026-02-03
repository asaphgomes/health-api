package com.healthtrack.health_api.dto;

import lombok.Getter;
import java.util.UUID;
import com.healthtrack.health_api.entity.User;

@Getter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Double currentImc;
    private Double currentWeight;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.currentImc = user.getImc();
        this.currentWeight = user.getWeight();
    }
}