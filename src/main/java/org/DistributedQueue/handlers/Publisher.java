package org.DistributedQueue.handlers;

import lombok.NonNull;
import org.DistributedQueue.models.Message;
import org.DistributedQueue.models.Topic;
import org.DistributedQueue.services.IPublisher;
import org.DistributedQueue.services.QueueSystem;

public class Publisher implements IPublisher {

    private final String id;
    private final QueueSystem queueSystem;

    public Publisher(@NonNull QueueSystem queueSystem, @NonNull String id) {
        this.queueSystem = queueSystem;
        this.id = id;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public synchronized void pushMessage(Message message, Topic topic) {
        queueSystem.publish(topic, message);
    }
}
