package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private StringJoiner tmp = new StringJoiner(System.lineSeparator());

    @Test
    public void whenServerAlwaysOff() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        tmp.add("200 10:56:01");
        tmp.add("");
        tmp.add("200 10:57:01");
        tmp.add("");
        tmp.add("200 10:59:01");
        tmp.add("");
        tmp.add("200 11:02:02");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println(tmp);
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertEquals(rsl.toString(), "csv");
    }

    @Test
    public void whenServerAlwaysOn() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        tmp.add("400 10:58:01");
        tmp.add("");
        tmp.add("500 11:01:02");
        tmp.add("");
        tmp.add("500 11:01:02");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println(tmp);
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        assertEquals(rsl.toString(), "csv" + System.lineSeparator() + "10:58:01;");
    }

    @Test
    public void whenServer2TimeOff() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        tmp.add("200 10:56:01");
        tmp.add("");
        tmp.add("200 10:57:01");
        tmp.add("");
        tmp.add("400 10:58:01");
        tmp.add("");
        tmp.add("200 10:59:01");
        tmp.add("");
        tmp.add("500 11:01:02");
        tmp.add("");
        tmp.add("200 11:02:02");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println(tmp);
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        }
        assertEquals(rsl.toString(), "csv" + System.lineSeparator()
                + "10:58:01;10:59:01;" + System.lineSeparator()
                + "11:01:02;11:02:02;");
    }
}