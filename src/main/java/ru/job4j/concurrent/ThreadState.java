package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {}
        );
        Thread second = new Thread(
                () -> {}
        );
        first.start();
        second.start();
        System.out.println(first.getName());
        System.out.println(second.getName());

        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState());
            System.out.println(second.getState());
        }

        System.out.println(Thread.currentThread().getName() + " Работа завершена.");
    }
}
