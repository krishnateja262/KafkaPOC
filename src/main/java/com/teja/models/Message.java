package com.teja.models;

import lombok.Data;

@Data
public class Message {
    String messageId;
    long timeStamp;
    String payload;
}
