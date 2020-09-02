package com.teja.queue;

import com.teja.models.Message;

import java.util.Optional;

class SimpleQueue {

    DoubleLinkedList head;
    DoubleLinkedList tail;
    long size;

    public SimpleQueue() {
        head = null;
        tail = null;
        size = 0L;
    }

    void push(Message message) {
        DoubleLinkedList node = new DoubleLinkedList(message);
        if (head == null) {
            head = node;
            tail = head;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
        size++;
    }

    Optional<Message> remove() {
        if (tail != null) {
            DoubleLinkedList node = head;
            head = head.next;
            size--;
            return Optional.of(node.message);
        } else {
            return Optional.empty();
        }
    }

    long size() {
        if (tail == null) return 0;
        return size;
    }
}
