package org.DistributedQueue.models;

import lombok.Getter;
import lombok.NonNull;
import org.DistributedQueue.handlers.ConsumerWorker;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {

    private final String topicName;
    private final String topicId;
    private final List<Message> messages;
    private final List<TopicConsumer> topicConsumers;

    public Topic(@NonNull String topicName, @NonNull String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        topicConsumers = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public synchronized void addMessage(Message message) {
        messages.add(message);
    }

    public void addConsumer(@NonNull TopicConsumer topicConsumer) {
        topicConsumers.add(topicConsumer);
    }
}
