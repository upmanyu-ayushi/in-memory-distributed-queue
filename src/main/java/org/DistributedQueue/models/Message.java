package org.DistributedQueue.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Message {

    private final String id;
    private final String msg;

    public Message(String msg) {
        this.msg = msg;
        this.id = UUID.randomUUID().toString();
    }
}
