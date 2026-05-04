package com.hamidou.exchangerateproxy.scheduler;

import com.hamidou.exchangerateproxy.dto.ExchangeRateResponse;
import com.hamidou.exchangerateproxy.service.ExchangeRateApiService;
import com.hamidou.exchangerateproxy.service.KafkaPublisherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Tâche planifiée qui récupère automatiquement les taux
 * puis les publie dans Kafka.
 */
@Component
public class ExchangeRateScheduler {

    private final ExchangeRateApiService exchangeRateApiService;
    private final KafkaPublisherService kafkaPublisherService;

    public ExchangeRateScheduler(ExchangeRateApiService exchangeRateApiService,
                                 KafkaPublisherService kafkaPublisherService) {
        this.exchangeRateApiService = exchangeRateApiService;
        this.kafkaPublisherService = kafkaPublisherService;
    }

    /**
     * Exécution automatique à intervalle fixe.
     * Ici, toutes les 60 secondes selon application.yaml.
     */
    @Scheduled(fixedRateString = "${app.exchange.polling-rate-ms}")
    public void fetchAndPublishRates() {
        // Appel de l'API externe
        ExchangeRateResponse response = exchangeRateApiService.fetchLatestRates();

        // Vérification simple avant publication
        if (response != null && response.getRates() != null) {
            kafkaPublisherService.publishRates(response);
            System.out.println("Tâche planifiée exécutée : taux publiés dans Kafka.");
        } else {
            System.out.println("Réponse API vide ou invalide.");
        }
    }
}