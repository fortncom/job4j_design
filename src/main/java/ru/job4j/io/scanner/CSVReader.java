package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static String readProp(String prop) {
        return prop.split("=")[1];
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Insufficient number of input parameters Usage java -jar dir.jar PATH DELIMITER OUT FILTER.");
        }
        Path start = Paths.get(readProp(args[0]));
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toFile().getAbsoluteFile()));
        }
        if (start.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("File is directory %s", start.toFile().getAbsoluteFile()));
        }
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(start).useDelimiter(readProp(args[1]))) {
            while (scanner.hasNext()) {
                String next = scanner.next();
                list.add("-" + next);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] fields = new String[list.size()];
        list.toArray(fields);
        ArgsName argsName = ArgsName.of(fields);
        String[] filter = readProp(args[3]).split(",");
        StringBuilder rsl = new StringBuilder();
        for (int i = 0; i < filter.length; i++) {
            rsl.append(String.format("%s=%s", filter[i], argsName.get(filter[i])).concat(";"));
        }
        String out = readProp(args[2]);
        if (out.equalsIgnoreCase("stdout")) {
            System.out.println(rsl);
        } else {
            try {
                Files.writeString(Path.of(out), rsl.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
