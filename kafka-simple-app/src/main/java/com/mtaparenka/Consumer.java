package com.mtaparenka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collection;
import java.util.Properties;

public class Consumer implements Closeable {
    private final org.apache.kafka.clients.consumer.Consumer<String, String> kafkaConsumer;

    public Consumer(String host, String consumerGroupId, Collection<String> topics) {
        Properties consumerProps = new Properties();
        consumerProps.put("bootstrap.servers", host);
        consumerProps.put("group.id", consumerGroupId);
        consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("auto.offset.reset", "earliest");
        this.kafkaConsumer = new KafkaConsumer<>(consumerProps);
        this.kafkaConsumer.subscribe(topics);
    }

    public ConsumerRecords<String, String> poll(Duration duration) {
        return this.kafkaConsumer.poll(duration);
    }

    public void close() {
        this.kafkaConsumer.close();
    }
}
