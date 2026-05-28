package com.grupo8.sistema_gestion_notas.controller;

import com.grupo8.sistema_gestion_notas.model.entity.AnalyticsData;
import com.grupo8.sistema_gestion_notas.service.AnalyticsDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsDataService service;

    public AnalyticsController(AnalyticsDataService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> recibir(@RequestBody Map<String, Object> payload) {
        AnalyticsData data = AnalyticsData.builder()
                .resumenMaterias(toJson(payload.get("resumen_materias")))
                .rendimientoPeriodo(toJson(payload.get("rendimiento_periodo")))
                .distribucionNotas(toJson(payload.get("distribucion_notas")))
                .resumenDocentes(toJson(payload.get("resumen_docentes")))
                .build();

        service.guardar(data);
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public ResponseEntity<AnalyticsData> obtener() {
        AnalyticsData data = service.obtenerUltimo();
        if (data == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(data);
    }

    private String toJson(Object obj) {
        if (obj == null) return "[]";
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            return "[]";
        }
    }
}
