package org.DistributedQueue;

import org.DistributedQueue.handlers.Publisher;
import org.DistributedQueue.models.Message;
import org.DistributedQueue.models.Topic;
import org.DistributedQueue.services.IConsumer;
import org.DistributedQueue.services.QueueSystem;

public class MainApp {
    public static void main(String[] args) {

        QueueSystem queue = new QueueSystem();
        Topic topic1 = queue.createTopic("topic 1");
        Topic topic2 = queue.createTopic("topic 2");

        Publisher publisher1 = new Publisher(queue, "1");
        Publisher publisher2 = new Publisher(queue, "2");

        Consumer consumer1 = new Consumer("1");
        Consumer consumer2 = new Consumer("2");
        Consumer consumer3 = new Consumer("3");
        Consumer consumer4 = new Consumer("4");
        Consumer consumer5 = new Consumer("5");

        queue.subscribe(consumer1, topic1);
        queue.subscribe(consumer2, topic1);
        queue.subscribe(consumer3, topic1);
        queue.subscribe(consumer4, topic1);
        queue.subscribe(consumer5, topic1);

        queue.subscribe(consumer1, topic2);
        queue.subscribe(consumer3, topic2);
        queue.subscribe(consumer4, topic2);

        Message message1 = new Message("message 1");
        Message message2 = new Message("message 2");
        Message message3 = new Message("message 3");
        Message message4 = new Message("message 4");
        Message message5 = new Message("message 5");

        publisher1.pushMessage(message1, topic1);
        publisher1.pushMessage(message2, topic1);
        publisher2.pushMessage(message3, topic1);
        publisher1.pushMessage(message4, topic2);
        publisher2.pushMessage(message5, topic2);

    }
}