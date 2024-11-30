package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        Integer currentCount;
        int newCount;
        do {
            currentCount = count.get();
            newCount = currentCount + 1;
        } while (!count.compareAndSet(currentCount, newCount));
    }

    public int get() {
        return count.get();
    }
}
