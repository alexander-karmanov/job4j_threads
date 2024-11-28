package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public final class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private final int size;

    public SimpleBlockingQueue(final int size) {
        this.size = size;
    }

    public synchronized void offer(final T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() > size) {
                this.wait();
            }
            queue.add(value);
            this.notifyAll();
        }

    }

    public synchronized T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                this.wait();
            }
            T result = queue.poll();
            this.notifyAll();
            return result;
        }
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
