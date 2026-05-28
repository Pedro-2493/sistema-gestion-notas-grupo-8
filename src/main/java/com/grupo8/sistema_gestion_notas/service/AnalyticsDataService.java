package com.grupo8.sistema_gestion_notas.service;

import com.grupo8.sistema_gestion_notas.model.entity.AnalyticsData;
import com.grupo8.sistema_gestion_notas.repository.AnalyticsDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnalyticsDataService {

    private final AnalyticsDataRepository repository;

    public AnalyticsDataService(AnalyticsDataRepository repository) {
        this.repository = repository;
    }

    public AnalyticsData guardar(AnalyticsData data) {
        data.setCreatedAt(LocalDateTime.now());
        return repository.save(data);
    }

    public AnalyticsData obtenerUltimo() {
        return repository.findTopByOrderByCreatedAtDesc()
                .orElse(null);
    }
}
