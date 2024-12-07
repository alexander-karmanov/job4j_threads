package ru.job4j.pool;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.ForkJoinPool;

public class ParallelSearchTest {
    @Test
    public void whenLinearSearchWithSmallArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        Integer target = 3;
        ParallelSearch<Integer> task = new ParallelSearch<>(array, target, 0, array.length);
        ForkJoinPool pool = new ForkJoinPool();
        Integer index = pool.invoke(task);
        assertThat(index.intValue()).isEqualTo(2);
    }

    @Test
    public void whenRecursiveSearchWithLargeArray() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Integer target = 10;
        ParallelSearch<Integer> task = new ParallelSearch<>(array, target, 0, array.length);
        ForkJoinPool pool = new ForkJoinPool();
        Integer index = pool.invoke(task);
        assertThat(index.intValue()).isEqualTo(9);
    }

    @Test
    public void whenRecursiveSearchWithLargeStringArray() {
        String[] array = {"BMW", "Mersedes", "Audi", "Hyundai", "Toyota", "Renault", "Peugeot",
                "Bentley", "Rolls-Royce", "Jaguar", "Kia", "Ford", "Honda", "Lincoln", "Jeep"};
        String target = "Ford";
        ParallelSearch<String> task = new ParallelSearch<>(array, target, 0, array.length);
        ForkJoinPool pool = new ForkJoinPool();
        Integer index = pool.invoke(task);
        assertThat(index.intValue()).isEqualTo(11);
    }

    @Test
    public void whenElementNotFoundInLargeArray() {
        String[] array = {"BMW", "Mersedes", "Audi", "Hyundai", "Toyota", "Renault", "Peugeot",
                "Bentley", "Rolls-Royce", "Jaguar", "Kia", "Ford", "Honda", "Lincoln", "Jeep"};
        String target = "Volkswagen";
        ParallelSearch<String> task = new ParallelSearch<>(array, target, 0, array.length);
        ForkJoinPool pool = new ForkJoinPool();
        Integer index = pool.invoke(task);
        assertThat(index.intValue()).isEqualTo(-1);
    }
}
