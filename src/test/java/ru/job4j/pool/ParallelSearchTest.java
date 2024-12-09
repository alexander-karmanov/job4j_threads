package ru.job4j.pool;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static ru.job4j.pool.ParallelSearch.search;

public class ParallelSearchTest {
    @Test
    public void whenLinearSearchWithSmallArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        assertThat(search(array, 3)).isEqualTo(2);
    }

    @Test
    public void whenRecursiveSearchWithLargeArray() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        assertThat(search(array, 10)).isEqualTo(9);
    }

    @Test
    public void whenRecursiveSearchWithLargeStringArray() {
        String[] array = {"BMW", "Mersedes", "Audi", "Hyundai", "Toyota", "Renault", "Peugeot",
                "Bentley", "Rolls-Royce", "Jaguar", "Kia", "Ford", "Honda", "Lincoln", "Jeep"};
        assertThat(search(array, "Ford")).isEqualTo(11);
    }

    @Test
    public void whenElementNotFoundInLargeArray() {
        String[] array = {"BMW", "Mersedes", "Audi", "Hyundai", "Toyota", "Renault", "Peugeot",
                "Bentley", "Rolls-Royce", "Jaguar", "Kia", "Ford", "Honda", "Lincoln", "Jeep"};
        assertThat(search(array, "Volkswagen")).isEqualTo(-1);
    }
}
