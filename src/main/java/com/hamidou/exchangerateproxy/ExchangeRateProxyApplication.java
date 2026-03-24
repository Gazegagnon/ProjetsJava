package com.hamidou.exchangerateproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Classe principale de l'application Spring Boot.
 */
@SpringBootApplication
@EnableScheduling // Active l'exécution des tâches planifiées
public class ExchangeRateProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRateProxyApplication.class, args);
    }
}