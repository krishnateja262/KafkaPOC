package com.teja.models;

import lombok.Data;

@Data
public class DoubleLinkedList {
    Message message;
    DoubleLinkedList next;
    DoubleLinkedList previous;

    public DoubleLinkedList(Message message) {
        this.message = message;
    }
}
