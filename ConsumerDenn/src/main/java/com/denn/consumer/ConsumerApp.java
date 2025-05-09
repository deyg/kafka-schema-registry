package com.denn.consumer;

import com.denn.avro.Cliente;
import org.apache.kafka.clients.consumer.*;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "grupo-cliente");
        props.put("key.deserializer", KafkaAvroDeserializer.class.getName());
        props.put("value.deserializer", KafkaAvroDeserializer.class.getName());
        props.put("schema.registry.url", "http://localhost:8081");
        props.put("specific.avro.reader", "true");

        Consumer<String, Cliente> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("clientes-avro"));

        while (true) {
            ConsumerRecords<String, Cliente> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, Cliente> record : records) {
                Cliente cliente = record.value();
                System.out.println("Recebido cliente: " + cliente);
            }
        }
    }
}
