package com.healthtrack.health_api.repository;

import com.healthtrack.health_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA cria a implementação automaticamente!
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}