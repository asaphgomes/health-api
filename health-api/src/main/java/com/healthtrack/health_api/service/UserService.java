package com.healthtrack.health_api.service;

import com.healthtrack.health_api.dto.UserRequestDTO;
import com.healthtrack.health_api.entity.User;
import com.healthtrack.health_api.entity.WeightHistory;
import com.healthtrack.health_api.exception.EmailAlreadyExistsException;
import com.healthtrack.health_api.repository.UserRepository;
import com.healthtrack.health_api.repository.WeightHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeightHistoryRepository weightHistoryRepository;

    @Transactional
    public User createUser(UserRequestDTO dto) {
        // 1. Verificar se email já existe
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email já cadastrado: " + dto.getEmail());
        }

        // 2. Criar a entidade User
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setBirthDate(dto.getBirthDate());
        user.setWeight(dto.getWeight());
        user.setHeight(dto.getHeight());

        // 3. Calcular o IMC
        double imc = calculateIMC(dto.getWeight(), dto.getHeight());
        user.setImc(imc);

        // 4. Salvar o usuário
        User savedUser = userRepository.save(user);

        WeightHistory firstRecord = new WeightHistory();
        firstRecord.setWeight(dto.getWeight());
        firstRecord.setImc(imc);
        firstRecord.setDate(LocalDate.now()); // data de hoje
        firstRecord.setUser(savedUser);

        weightHistoryRepository.save(firstRecord);
        return savedUser;
    }

    // Méttodo privado para calcular IMC
    private double calculateIMC(double weight, double height) {
        double imc = weight / (height * height);
        return Math.round(imc * 100.0) / 100.0;
    }
}
