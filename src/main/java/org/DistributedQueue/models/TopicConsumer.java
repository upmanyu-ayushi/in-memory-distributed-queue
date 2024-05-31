package org.DistributedQueue.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.DistributedQueue.services.IConsumer;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class TopicConsumer {

    private final AtomicInteger offset;
    private final IConsumer consumer;

    public TopicConsumer(@NonNull IConsumer consumer) {
        this.consumer = consumer;
        offset = new AtomicInteger(0);
    }

}
