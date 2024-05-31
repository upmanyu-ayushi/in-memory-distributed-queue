package org.DistributedQueue.services;

import org.DistributedQueue.models.Message;
import org.DistributedQueue.models.Topic;

public interface IPublisher {

    String getId();
    void pushMessage(Message message, Topic topic);
}
