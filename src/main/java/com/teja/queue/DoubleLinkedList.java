package com.teja.queue;

import com.teja.models.Message;

public class DoubleLinkedList {
    Message message;
    DoubleLinkedList next;
    DoubleLinkedList previous;

    public DoubleLinkedList(Message message) {
        this.message = message;
    }
}
