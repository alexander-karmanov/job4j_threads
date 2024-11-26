package ru.job4j.synch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlockingQueueTest {

    @Test
    void testQueue() {
        List<Integer> rsl = new ArrayList<>();
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread producer = new Thread(() -> {
            try {
                queue.offer(1);
                queue.offer(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                rsl.add(queue.poll());
                rsl.add(queue.poll());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        consumer.start();
        try {
            consumer.join();
            producer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThat(rsl).hasSize(2);
    }
}
