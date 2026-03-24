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

    // URL d'Elasticsearch
    @Value("${elasticsearch.url}")
    private String elasticsearchUrl;

    // Nom de l'index Elasticsearch
    @Value("${elasticsearch.index-name}")
    private String indexName;

    // Client HTTP Spring
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Envoie un document JSON dans Elasticsearch.
     *
     * @param jsonMessage message JSON à indexer
     */
    public void indexJsonDocument(String jsonMessage) {
        // URL d'insertion d'un document dans l'index
        String url = elasticsearchUrl + "/" + indexName + "/_doc";

        // En-têtes HTTP indiquant qu'on envoie du JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Création de la requête HTTP avec le JSON en corps
        HttpEntity<String> request = new HttpEntity<>(jsonMessage, headers);

        // Envoi du document à Elasticsearch
        String response = restTemplate.postForObject(url, request, String.class);

        System.out.println("Document indexé dans Elasticsearch : " + response);
    }
}