package ru.job4j.synch;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class SimpleBlockingQueueTest2 {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(2, 7).forEach(
                            (v) -> {
                                try {
                                    queue.offer(v);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).containsExactly(2, 3, 4, 5, 6);
    }
}
