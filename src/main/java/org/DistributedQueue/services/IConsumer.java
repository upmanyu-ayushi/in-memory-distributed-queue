package org.DistributedQueue.services;

import org.DistributedQueue.models.Message;

public interface IConsumer {

    String getId();
    void consume(Message message);

}
