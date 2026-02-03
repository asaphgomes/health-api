package com.healthtrack.health_api.repository;

import com.healthtrack.health_api.entity.WeightHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightHistoryRepository extends JpaRepository<WeightHistory, Long> {

    // Buscar histórico de um usuário específico, ordenado por data (mais recente primeiro)
    List<WeightHistory> findByUserIdOrderByDateDesc(Long userId);
}