package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.pools.RolColSum.*;

public class RolColSumTest {
    @Test
    void whenSum() throws InterruptedException {
        int[][] matrix = {{5, 7, 8}, {1, 5, 10}, {9, 3, 2}};
        Sums[] sums = sum(matrix);
        Sums[] expected = {
                new Sums(20, 15),
                new Sums(16, 15),
                new Sums(14, 20)
        };
        Thread.sleep(1000);
        assertThat(sums).isEqualTo(expected);
    }

    @Test
    public void whenAsyncSum() throws InterruptedException {
        int[][] array = {{10, 1, 6}, {8, 5, 11}, {12, 2, 4}};
        Sums[] sums = asyncSum(array);
        Sums[] expected = new Sums[]{
                new Sums(17, 30),
                new Sums(24, 8),
                new Sums(18, 21)
        };
        Thread.sleep(1000);
        assertThat(sums).isEqualTo(expected);
    }
}
