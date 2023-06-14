# KSQLDB

## Prérerquis

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Installation de Kafka

```bash
docker-compose up -d
```

## Composition du cluster

### Les services

- 1 Kafka singlenode en mode KRAFT
- 1 KAfka-ui
- 1 KSQlDB
- 1 KSQlDB-cli
  
### Les topics

- input: entrée des données
- output: sortie des données

## ouvrir le CLI

```bash
docker exec -it ksqldb-cli ksql http://ksqldb:8088
```

## Création des streams

Le stream `calc_input` est créé à partir du topic `input` Kafka.
```sql
CREATE STREAM calc_input
  (a INT, b INT)
WITH (
  kafka_topic='input',
  value_format='json',
  partitions=1
);
```

Le stream `calc_output` est créé à partir du topic `output` Kafka.
```sql
CREATE STREAM calc_output
WITH (
  kafka_topic='output',
  value_format='json',
  partitions=1    
) 
AS SELECT a, b, a + b AS result FROM calc_input;
```

### Exemple de fenêtre

```sql
SELECT 
  COUNT(*)
FROM
  calc_output
WINDOW TUMBLING (SIZE 1 MINUTE)
EMIT CHANGES;
```