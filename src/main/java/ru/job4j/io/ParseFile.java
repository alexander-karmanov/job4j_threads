package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Character> filter) throws IOException {
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file))) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = input.read()) != -1) {
                char character = (char) data;
                if (filter.test(character)) {
                    output.append(character);
                }
            }
            return output.toString();
        }
    }

    public synchronized String getContent() throws IOException {
        return getContent(c -> true);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return getContent(c -> c < 0x80);
    }
}
