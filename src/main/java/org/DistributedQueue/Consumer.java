package org.DistributedQueue;

import org.DistributedQueue.models.Message;
import org.DistributedQueue.services.IConsumer;

public class Consumer implements IConsumer {

    private final String id;

    public Consumer(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(Message message) {
         System.out.println("Consumer: " + id + " Consumed message id: " + message.getId() + ", content: " + message.getMsg());
    }
}
