package com.teja.queue;

import com.teja.models.DoubleLinkedList;
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
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
        size++;
    }

    Optional<Message> remove() {
        if (tail != null) {
            DoubleLinkedList node = head;
            head = head.getNext();
            size--;
            return Optional.of(node.getMessage());
        } else {
            return Optional.empty();
        }
    }

    Optional<DoubleLinkedList> nextMessage(DoubleLinkedList dll){
        if(dll==null){
            return Optional.of(head);
        } else if(dll.getNext()==null){
            return Optional.empty();
        }else{
            return Optional.of(dll.getNext());
        }
    }

    long getSize() {
        if (tail == null) return 0;
        return size;
    }
}
