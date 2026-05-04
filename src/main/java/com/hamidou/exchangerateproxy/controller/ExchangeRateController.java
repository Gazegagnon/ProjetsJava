package com.hamidou.exchangerateproxy.controller;

import com.hamidou.exchangerateproxy.dto.ExchangeRateResponse;
import com.hamidou.exchangerateproxy.service.ExchangeRateApiService;
import com.hamidou.exchangerateproxy.service.KafkaPublisherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST pour récupérer et publier les taux de change.
 */
@RestController
public class ExchangeRateController {

    private final ExchangeRateApiService exchangeRateApiService;
    private final KafkaPublisherService kafkaPublisherService;

    public ExchangeRateController(ExchangeRateApiService exchangeRateApiService,
                                  KafkaPublisherService kafkaPublisherService) {
        this.exchangeRateApiService = exchangeRateApiService;
        this.kafkaPublisherService = kafkaPublisherService;
    }

    /**
     * Retourne les derniers taux récupérés depuis l'API externe.
     */
    @GetMapping("/rates/latest")
    public ExchangeRateResponse getLatestRates() {
        return exchangeRateApiService.fetchLatestRates();
    }

    /**
     * Récupère les taux puis les publie dans Kafka.
     */
    @GetMapping("/rates/publish")
    public String publishRates() {
        ExchangeRateResponse response = exchangeRateApiService.fetchLatestRates();
        kafkaPublisherService.publishRates(response);
        return "Les taux ont été publiés dans Kafka.";
    }
}