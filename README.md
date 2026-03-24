\# Exchange Rate Proxy



\## Objectif

Cette application Java Spring Boot sert de proxy entre une API externe de taux de change et les équipes internes de l'entreprise.
Elle centralise la récupération des taux de change à partir de l' API externe, publie les données dans Kafka, les stocke dans Elasticsearch et permet leur visualisation dans Kibana.



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

