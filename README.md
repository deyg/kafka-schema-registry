
# Kafka Avro Demo com Schema Registry

## Requisitos
- Java 11
- Maven
- Docker e Docker Compose

## Subir infraestrutura Kafka + Schema Registry + Kafdrop

```bash
docker-compose up -d
```

Acesse o Kafdrop em [http://localhost:9000](http://localhost:9000)

## Executar o Producer

```bash
cd ProducerDenn
mvn clean install
java -cp target/ProducerDenn-1.0-SNAPSHOT.jar com.denn.producer.ProducerApp
```

## Executar o Consumer

```bash
cd ConsumerDenn
mvn clean install
java -cp target/ConsumerDenn-1.0-SNAPSHOT.jar com.denn.consumer.ConsumerApp
```

## Estrutura da Mensagem (Avro)
```json
{
  "type": "record",
  "name": "Cliente",
  "namespace": "com.denn.avro",
  "fields": [
    {"name": "nome", "type": "string"},
    {"name": "nascimento", "type": {"type": "int", "logicalType": "date"}},
    {"name": "salario", "type": "double"}
  ]
}
```
