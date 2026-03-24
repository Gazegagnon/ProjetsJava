package com.hamidou.exchangerateproxy.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service consommateur Kafka.
 * Il lit les messages du topic puis les envoie vers Elasticsearch.
 */
@Service
public class KafkaConsumerService {

    private final ElasticsearchIndexService elasticsearchIndexService;

    public KafkaConsumerService(ElasticsearchIndexService elasticsearchIndexService) {
        this.elasticsearchIndexService = elasticsearchIndexService;
    }

    /**
     * Méthode appelée automatiquement à chaque message reçu sur le topic Kafka.
     *
     * @param message message JSON reçu depuis Kafka
     */
    @KafkaListener(topics = "${app.exchange.topic-name}", groupId = "exchange-rate-consumer-group")
    public void consume(String message) {
        System.out.println("Message reçu depuis Kafka : " + message);

        // Envoi du JSON dans Elasticsearch
        elasticsearchIndexService.indexJsonDocument(message);
    }
}