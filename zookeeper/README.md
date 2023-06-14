# Cluster Kafka avec Zookeeper

## Prérerquis

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Installation de Kafka

```bash
docker-compose up -d
```

## Composition du cluster

- Zookeeper : 3 noeuds
  - zookeeper1
  - zookeeper2
  - zookeeper3
- Kafka : 3 noeuds
  - kafka1
  - kafka2
  - kafka3
- Kafka UI : 1 noeud
  - kafka-ui
- Zookeeper UI : 1 noeud
  - zookeeper-ui