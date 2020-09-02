package com.teja;


import com.teja.consumer.KafkaConsumer;
import com.teja.producer.KafkaProducer;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    private static final String QNAME = "krsna";

    public static void main(String[] args) throws InterruptedException {
        KafkaConsumer consumer = new KafkaConsumer();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            while (true) {
                try {
                    Optional<String> payload = consumer.consume(QNAME);
                    payload.ifPresent(System.out::println);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(1000);
        KafkaProducer producer = new KafkaProducer();
        for(int i=0;i<100;i++){
            Thread.sleep(100);
            producer.send(QNAME, "Hello world: "+i);
        }
    }
}
