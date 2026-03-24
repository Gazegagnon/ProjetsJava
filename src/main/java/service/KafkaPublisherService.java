package com.hamidou.exchangerateproxy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamidou.exchangerateproxy.dto.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Service chargé d'envoyer les taux dans Kafka.
 */
@Service
public class KafkaPublisherService {

    // Objet Spring permettant d'envoyer des messages Kafka
    private final KafkaTemplate<String, String> kafkaTemplate;

    // Objet permettant de convertir un objet Java en JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Nom du topic Kafka lu depuis application.yaml
    @Value("${app.exchange.topic-name}")
    private String topicName;

    public KafkaPublisherService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Convertit la réponse des taux en JSON puis l'envoie dans Kafka.
     *
     * @param response objet contenant les taux de change
     */
    public void publishRates(ExchangeRateResponse response) {
        try {
            // Conversion de l'objet Java en texte JSON
            String jsonMessage = objectMapper.writeValueAsString(response);

            // Envoi dans Kafka
            kafkaTemplate.send(topicName, response.getBase(), jsonMessage);

            System.out.println("Message envoyé dans Kafka : " + jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors de la conversion JSON", e);
        }
    }
}