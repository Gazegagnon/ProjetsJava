package com.hamidou.exchangerateproxy.dto;

import java.util.Map;

/**
 * Représente la réponse JSON de l'API externe des taux de change.
 */
public class ExchangeRateResponse {

    // Devise de base, par exemple USD
    private String base;

    // Date renvoyée par l'API
    private String date;

    // Liste des taux par devise
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}