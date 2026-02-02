package com.healthtrack.health_api.controller;

import com.healthtrack.health_api.dto.UserRequestDTO;
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
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequestDTO dto) {
        User savedUser = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}