package com.healthtrack.health_api.controller;

import com.healthtrack.health_api.dto.UserRequestDTO;
import com.healthtrack.health_api.dto.UserResponseDTO;
import com.healthtrack.health_api.entity.User;
import com.healthtrack.health_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        User savedUser = userService.createUser(dto);
        UserResponseDTO response = new UserResponseDTO(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}