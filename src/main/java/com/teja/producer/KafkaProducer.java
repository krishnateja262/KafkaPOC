package com.teja.producer;

import com.teja.queue.KafkaBaby;
import com.teja.models.Message;

import java.util.Date;
import java.util.UUID;

public class KafkaProducer {
    String id;

    public KafkaProducer() {
        id = UUID.randomUUID().toString();
    }

    public void send(String qName, String payload){
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString());
        message.setTimeStamp(new Date().getTime());
        message.setPayload(payload);
        KafkaBaby.getQueueManager().produce(qName, message);
    }
}
