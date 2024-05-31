package org.DistributedQueue.services;

import lombok.NonNull;
import org.DistributedQueue.handlers.TopicHandler;
import org.DistributedQueue.models.Message;
import org.DistributedQueue.models.Topic;
import org.DistributedQueue.models.TopicConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QueueSystem {

    private final Map<String, TopicHandler> topicToTopicHandler;

    public QueueSystem() {
        this.topicToTopicHandler = new HashMap<>();
    }

    public Topic createTopic(@NonNull String topicName) {

        Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topicToTopicHandler.put(topic.getTopicId(), topicHandler);
        System.out.println("Created a topic");
        return topic;
    }

    public void subscribe(@NonNull IConsumer consumer, @NonNull Topic topic) {
        TopicConsumer topicConsumer = new TopicConsumer(consumer);
        topic.addConsumer(topicConsumer);
        System.out.println(consumer.getId() + " subscribed to topic : " + topic.getTopicName());
    }

    public void publish(@NonNull Topic topic, Message message) {
        topic.addMessage(message);
        System.out.println(message.getMsg() + " published to topic: " + topic.getTopicName());
        new Thread(() -> topicToTopicHandler.get(topic.getTopicId()).notifyConsumers()).start();
    }
}
