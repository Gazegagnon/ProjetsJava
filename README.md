\# Exchange Rate Proxy



\## Objectif

Cette application Java Spring Boot centralise la récupération des taux de change à partir d'une API externe, publie les données dans Kafka, les stocke dans Elasticsearch et permet leur visualisation dans Kibana.



\## Fonctionnement

1\. L'application appelle l'API externe de taux de change

2\. Les taux sont publiés dans un topic Kafka

3\. Un consommateur Kafka lit les messages

4\. Les messages sont indexés dans Elasticsearch

5\. Kibana permet la visualisation des données



\## Technologies

\- Java 17

\- Spring Boot

\- Apache Kafka

\- Elasticsearch

\- Kibana

\- Docker Compose

\- Maven



\## Lancement de l'environnement

```bash

docker compose up -d

