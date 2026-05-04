package com.hamidou.exchangerateproxy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service chargé d'envoyer les documents JSON vers Elasticsearch.
 */
@Service
public class ElasticsearchIndexService {

    @Value("${elasticsearch.url}")
    private String elasticsearchUrl;

    @Value("${elasticsearch.index-name}")
    private String indexName;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Indexe un document JSON dans Elasticsearch.
     *
     * @param jsonMessage message JSON à indexer
     */
    public void indexJsonDocument(String jsonMessage) {
        try {
            String url = elasticsearchUrl + "/" + indexName + "/_doc";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(jsonMessage, headers);

            String response = restTemplate.postForObject(url, request, String.class);
            System.out.println("Document indexé dans Elasticsearch : " + response);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'indexation Elasticsearch : " + e.getMessage());
        }
    }
}