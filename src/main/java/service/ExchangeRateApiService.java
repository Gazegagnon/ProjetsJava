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

    // URL de l'API lue depuis application.yaml
    @Value("${app.exchange.api-url}")
    private String apiUrl;

    // Client HTTP simple fourni par Spring
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Appelle l'API externe et convertit la réponse JSON en objet Java.
     *
     * @return la réponse des taux de change
     */
    public ExchangeRateResponse fetchLatestRates() {
        return restTemplate.getForObject(apiUrl, ExchangeRateResponse.class);
    }
}