package com.teja.queue;

import com.teja.models.DoubleLinkedList;
import com.teja.models.Message;

import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentQueue {
    private ReentrantLock lock;
    private Condition queueEmptyCondition;

    private SimpleQueue simpleQueue;

    public ConcurrentQueue() {
        lock = new ReentrantLock();
        queueEmptyCondition = lock.newCondition();
        simpleQueue = new SimpleQueue();
    }

    public void push(Message message) {
        try {
            lock.lock();
            simpleQueue.push(message);
            queueEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Optional<DoubleLinkedList> fetchNextNode(DoubleLinkedList doubleLinkedList) {
        Optional<DoubleLinkedList> response;
        try {
            lock.lock();
            while (simpleQueue.getSize() == 0L) {
                queueEmptyCondition.await();
            }
            response = simpleQueue.nextMessage(doubleLinkedList);
        } catch (InterruptedException e) {
            e.printStackTrace();
            response = Optional.empty();
        }finally {
            lock.unlock();
        }
        return response;
    }
}
