package com.hamidou.exchangerateproxy.controller;

import com.hamidou.exchangerateproxy.dto.ExchangeRateResponse;
import com.hamidou.exchangerateproxy.service.ExchangeRateApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST pour exposer les taux récupérés depuis l'API externe.
 */
@RestController
public class ExchangeRateController {

    private final ExchangeRateApiService exchangeRateApiService;

    public ExchangeRateController(ExchangeRateApiService exchangeRateApiService) {
        this.exchangeRateApiService = exchangeRateApiService;
    }

    /**
     * Endpoint de test pour récupérer les derniers taux.
     *
     * URL : http://localhost:8081/rates/latest
     *
     * @return les taux récupérés depuis l'API externe
     */
    @GetMapping("/rates/latest")
    public ExchangeRateResponse getLatestRates() {
        return exchangeRateApiService.fetchLatestRates();
    }
}