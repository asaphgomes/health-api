package com.healthtrack.health_api.service;

import com.healthtrack.health_api.dto.UserRequestDTO;
import com.healthtrack.health_api.entity.User;
import com.healthtrack.health_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserRequestDTO dto) {
        // 1. Verificar se email já existe
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado!");
        }

        // 2. Criar a entidade User
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthDate(dto.getBirthDate());
        user.setWeight(dto.getWeight());
        user.setHeight(dto.getHeight());

        // 3. Calcular o IMC automaticamente
        double imc = calculateIMC(dto.getWeight(), dto.getHeight());
        user.setImc(imc);

        // 4. Salvar no banco
        return userRepository.save(user);
    }

    // Méttodo privado para calcular IMC
    private double calculateIMC(double weight, double height) {
        double imc = weight / (height * height);
        return Math.round(imc * 100.0) / 100.0;
    }
}
