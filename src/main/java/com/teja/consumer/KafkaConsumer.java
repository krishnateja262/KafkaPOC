package com.teja.consumer;

import com.teja.queue.KafkaBaby;
import com.teja.models.Message;

import java.util.Optional;
import java.util.UUID;

public class KafkaConsumer {
    String id;

    public KafkaConsumer() {
        this.id = UUID.randomUUID().toString();
    }

    public Optional<String> consume(String qName) {
        Message message = KafkaBaby.getQueueManager().consume(qName, id);
        if (message == null){
            return Optional.empty();
        }
        return Optional.of(message.getPayload());
    }
}
