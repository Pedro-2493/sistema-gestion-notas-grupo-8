package com.grupo8.sistema_gestion_notas.repository;

import com.grupo8.sistema_gestion_notas.model.entity.AnalyticsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalyticsDataRepository extends JpaRepository<AnalyticsData, Long> {
    Optional<AnalyticsData> findTopByOrderByCreatedAtDesc();
}
