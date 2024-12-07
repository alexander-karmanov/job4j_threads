package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch <T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T target;
    private final int start;
    private final int end;

    private static final int THRESHOLD = 10;

    public ParallelSearch(T[] array, T target, int start, int end) {
        this.array = array;
        this.target = target;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int length = end - start;

        if (length <= THRESHOLD) {
            return linearSearch();
        }

        int mid = (start + end) / 2;
        ParallelSearch<T> leftTask = new ParallelSearch<>(array, target, start, mid);
        ParallelSearch<T> rightTask = new ParallelSearch<>(array, target, mid, end);
        leftTask.fork();
        int rightResult = rightTask.compute();
        int leftResult = leftTask.join();

        return (leftResult != -1) ? leftResult : rightResult;
    }

    private Integer linearSearch() {
        for (int i = start; i < end; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        Integer target = 7;
        ParallelSearch<Integer> task = new ParallelSearch<>(array, target, 0, array.length);
        Integer index = pool.invoke(task);
        System.out.println("Индекс элемента " + target + ": " + index);
    }
}
