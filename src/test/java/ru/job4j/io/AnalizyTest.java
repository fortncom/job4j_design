package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AnalizyTest {

    @Test
    public void whenServerAlwaysOff() {
        String pathIn = "src/main/java/ru/job4j/res/server.1.log";
        String pathOut = "src/main/java/ru/job4j/res/unavailable.1.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(pathIn, pathOut);
        assertEquals(analizy.getLogUnavailable().toString(), "");
    }

    @Test
    public void whenServerAlwaysOn() {
        String pathIn = "src/main/java/ru/job4j/res/server.2.log";
        String pathOut = "src/main/java/ru/job4j/res/unavailable.2.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(pathIn, pathOut);
        StringBuilder rsl = new StringBuilder("");
        try (BufferedReader in = new BufferedReader(new FileReader(pathOut))) {
            in.readLine();
            rsl.append(in.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assertEquals(rsl.toString(), analizy.getLogUnavailable().toString());
    }

    @Test
    public void whenServer2TimeOff() {
        String pathIn = "src/main/java/ru/job4j/res/server.3.log";
        String pathOut = "src/main/java/ru/job4j/res/unavailable.3.csv";
        Analizy analizy = new Analizy();
        analizy.unavailable(pathIn, pathOut);
        StringBuilder rsl = new StringBuilder("");
        try (BufferedReader in = new BufferedReader(new FileReader(pathOut))) {
            in.readLine();
            rsl.append(in.readLine());
            rsl.append(System.lineSeparator());
            rsl.append(in.readLine());
            rsl.append(System.lineSeparator());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assertEquals(rsl.toString(), analizy.getLogUnavailable().toString());
    }
}