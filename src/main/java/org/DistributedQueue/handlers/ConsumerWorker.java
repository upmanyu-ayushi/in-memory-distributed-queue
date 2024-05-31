package org.DistributedQueue.handlers;

import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.DistributedQueue.models.Message;
import org.DistributedQueue.models.Topic;
import org.DistributedQueue.models.TopicConsumer;


@Getter
public class ConsumerWorker implements Runnable {

    private final Topic topic;
    private final TopicConsumer topicConsumer;

    ConsumerWorker(@NonNull Topic topic, @NonNull TopicConsumer topicConsumer) {
        this.topic = topic;
        this.topicConsumer = topicConsumer;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (topicConsumer) {
            do {
                int currOffset = topicConsumer.getOffset().get();
                while(currOffset >= topic.getMessages().size()) {
                    topicConsumer.wait();
                }
                Message message = topic.getMessages().get(currOffset);
                topicConsumer.getConsumer().consume(message);

                // We cannot just increment here since subscriber offset can be reset while it is consuming. So, after
                // consuming we need to increase only if it was previous one.
                // topicConsumer.getOffset().compareAndSet(curOffset, curOffset + 1);
                topicConsumer.getOffset().set(currOffset + 1);

            } while (true);

        }
    }

    public synchronized void wakeUpIfNeeded() {
        synchronized (topicConsumer) {
            topicConsumer.notify();
        }
    }
}
