package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    private String fileName;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            downloadFile(url, speed, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadFile(String url, int speed, String fileName) throws IOException {
        var out = fileName;

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(out)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            long totalBytesRead = 0;

            while ((bytesRead = in.read(dataBuffer)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                totalBytesRead += bytesRead;

                long finish = System.currentTimeMillis();
                long duration = finish - startTime;

                    if (totalBytesRead >= speed) {
                        if (duration < 1000) {
                            try {
                                Thread.sleep(1000 - duration);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                System.out.println("Скачивание прервано.");
                                return;
                            }
                    }
                    totalBytesRead = 0;

                }
                startTime = System.currentTimeMillis();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Not enough parameters.");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        try {
            wget.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
