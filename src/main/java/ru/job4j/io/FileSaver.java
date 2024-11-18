package ru.job4j.io;

import java.io.*;

public class FileSaver {
    private final File file;

    public FileSaver(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            out.write(content);
        }
    }
}
