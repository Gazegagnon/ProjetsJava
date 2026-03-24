package com.hamidou.exchangerateproxy.service;

import com.hamidou.exchangerateproxy.dto.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service chargé d'appeler l'API externe de taux de change.
 */
@Service
public class ExchangeRateApiService {

    @Value("${app.exchange.api-url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Appelle l'API externe et retourne les taux.
     *
     * @return la réponse des taux de change, ou null en cas d'erreur
     */
    public ExchangeRateResponse fetchLatestRates() {
        try {
            return restTemplate.getForObject(apiUrl, ExchangeRateResponse.class);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'appel à l'API externe : " + e.getMessage());
            return null;
        }
    }
}