package com.mtaparenka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

class MainTest {
    @Test
    void test() {
        String host = "localhost:29092";
        String topic = "test-topic-2" + UUID.randomUUID();
        String message = "Hello from java app";

        Producer producer = new Producer(host);
        Consumer consumer = new Consumer(host, UUID.randomUUID().toString(), List.of(topic));

        producer.send(topic, message);
        ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(2L));
        Assertions.assertEquals(1, poll.count());
        Assertions.assertEquals(message, poll.iterator().next().value());
    }
}