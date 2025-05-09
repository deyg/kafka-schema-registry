package com.denn.producer;

import com.denn.avro.Cliente;
import org.apache.kafka.clients.producer.*;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

public class ProducerApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", KafkaAvroSerializer.class.getName());
        props.put("value.serializer", KafkaAvroSerializer.class.getName());
        props.put("schema.registry.url", "http://localhost:8081");

        Producer<String, Cliente> producer = new KafkaProducer<>(props);

        Cliente cliente = new Cliente(
                "João da Silva",
                 LocalDate.of(1990, 5, 15),
                4560.75
        );

        ProducerRecord<String, Cliente> record = new ProducerRecord<>("clientes-avro", cliente);

        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.println("Mensagem enviada: " + metadata.toString());
            } else {
                exception.printStackTrace();
            }
        });

        producer.flush();
        producer.close();
    }
}
