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

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${app.exchange.topic-name}")
    private String topicName;

    public KafkaPublisherService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Convertit les taux en JSON puis les publie dans Kafka.
     *
     * @param response objet contenant les taux
     */
    public void publishRates(ExchangeRateResponse response) {
        try {
            if (response == null) {
                System.out.println("Publication annulée : réponse API nulle.");
                return;
            }

            String jsonMessage = objectMapper.writeValueAsString(response);
            kafkaTemplate.send(topicName, response.getBase(), jsonMessage);

            System.out.println("Message envoyé dans Kafka.");
        } catch (JsonProcessingException e) {
            System.out.println("Erreur de conversion JSON : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi Kafka : " + e.getMessage());
        }
    }
}