package org.DistributedQueue.handlers;

import lombok.NonNull;
import org.DistributedQueue.models.Topic;
import org.DistributedQueue.models.TopicConsumer;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
    private final Topic topic;
    private final Map<String, ConsumerWorker> consumerIdToConsumerWorker;

    public TopicHandler(@NonNull Topic topic) {
        this.topic = topic;
        consumerIdToConsumerWorker = new HashMap<>();
    }

    public void notifyConsumers() {
        for(TopicConsumer topicConsumer : topic.getTopicConsumers()) {
            startConsumerWorker(topicConsumer);
        }

    }

    private void startConsumerWorker(TopicConsumer topicConsumer) {
        String consumerId = topicConsumer.getConsumer().getId();
        if (!consumerIdToConsumerWorker.containsKey(consumerId)) {
            ConsumerWorker consumerWorker = new ConsumerWorker(topic, topicConsumer);
            consumerIdToConsumerWorker.put(consumerId, consumerWorker);
            new Thread(consumerWorker).start();
        }
        ConsumerWorker consumerWorker = consumerIdToConsumerWorker.get(consumerId);
        consumerWorker.wakeUpIfNeeded();
    }
}
