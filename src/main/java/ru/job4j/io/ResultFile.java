package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("src/main/resources/result.txt")) {
            out.write("Hello, world!".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (FileOutputStream out = new FileOutputStream("src/main/resources/multiplicationTable.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    out.write((i * j + " ").getBytes());
                }
                out.write(("\n").getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
