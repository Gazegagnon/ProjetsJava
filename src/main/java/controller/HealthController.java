package com.hamidou.exchangerateproxy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur de test pour vérifier que l'application fonctionne
 */
@RestController
public class HealthController {

    /**
     * Endpoint de test
     * Accessible via : http://localhost:8080/health
     */
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}