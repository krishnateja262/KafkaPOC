package com.teja.queue;

import com.teja.models.DoubleLinkedList;
import com.teja.models.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QueueManager {

    private Map<String, ConcurrentQueue> map;
    private Map<String, DoubleLinkedList> consumerMap;

    private QueueManager() {
        this.map = new HashMap<>();
        this.consumerMap = new HashMap<>();
    }

    private boolean checkQueueExists(String qName){
        return map.containsKey(qName);
    }

    private void createQueue(String qName){
        ConcurrentQueue concurrentQueue = new ConcurrentQueue();
        map.put(qName, concurrentQueue);
    }

    public void removeQueue(String qName){

    }

    public void produce(String qName, Message message){
        if(!checkQueueExists(qName)){
            createQueue(qName);
        }
        map.get(qName).push(message);
    }

    public Message consume(String qName, String consumerId){
        if(!checkQueueExists(qName)){
            createQueue(qName);
        }
        if(!consumerMap.containsKey(consumerId)){
            consumerMap.put(consumerId, null);
        }
        DoubleLinkedList presentMessage = consumerMap.get(consumerId);
        Optional<DoubleLinkedList> newMessage = map.get(qName).fetchNextNode(presentMessage);

        if(newMessage.isPresent()){
            consumerMap.put(consumerId, newMessage.get());
            return newMessage.get().getMessage();
        }else{
            return null;
        }
    }

    public static QueueManager getQueueManager(){
        return new QueueManager();
    }

}
