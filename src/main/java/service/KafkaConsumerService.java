package com.hamidou.exchangerateproxy.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service consommateur Kafka.
 */
@Service
public class KafkaConsumerService {

    private final ElasticsearchIndexService elasticsearchIndexService;

    public KafkaConsumerService(ElasticsearchIndexService elasticsearchIndexService) {
        this.elasticsearchIndexService = elasticsearchIndexService;
    }

    /**
     * Lit les messages Kafka puis les envoie dans Elasticsearch.
     *
     * @param message message JSON reçu depuis Kafka
     */
    @KafkaListener(topics = "${app.exchange.topic-name}", groupId = "exchange-rate-consumer-group")
    public void consume(String message) {
        try {
            System.out.println("Message reçu depuis Kafka.");
            elasticsearchIndexService.indexJsonDocument(message);
        } catch (Exception e) {
            System.out.println("Erreur dans le consumer Kafka : " + e.getMessage());
        }
    }
}