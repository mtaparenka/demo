package com.mtaparenka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.Future;

public class Producer implements Closeable {
    private final org.apache.kafka.clients.producer.Producer<String, String> kafkaProducer;

    public Producer(String host) {
        Properties props = new Properties();
        props.put("bootstrap.servers", host);
        props.put("linger.ms", 5);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.kafkaProducer = new KafkaProducer<>(props);
    }

    public Future<RecordMetadata> send(String topic, String value) {
        return this.kafkaProducer.send(new ProducerRecord<>(topic, value));
    }

    public void close() {
        this.kafkaProducer.close();
    }
}