package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CASCountTest {
    @Test
    void whenOneThread() {
        CASCount count = new CASCount();
        count.increment();
        count.increment();
        assertThat(count.get()).isEqualTo(2);
    }

    @Test
    public void whenIncrementMultiThread() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(count::increment);
        Thread second = new Thread(count::increment);
        Thread third = new Thread(count::increment);
        first.start();
        second.start();
        third.start();
        first.join();
        second.join();
        third.join();
        assertThat(count.get()).isEqualTo(3);
    }
}
