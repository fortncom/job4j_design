package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
        StringBuilder rsl;
        try (FileOutputStream out = new FileOutputStream("src/main/resources/result.txt")) {
            rsl = new StringBuilder("Hello, world!");
            out.write(rsl.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (FileOutputStream out = new FileOutputStream("src/main/resources/multiplicationTable.txt")) {
            rsl = new StringBuilder();
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    rsl.append(i * j).append(" ");
                }
                rsl.append(System.lineSeparator());
            }
            out.write(rsl.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
