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

    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Insufficient number of input parameters Usage java -jar dir.jar PATH DELIMITER OUT FILTER.");
        }
        ArgsName argsName = ArgsName.of(args);
        Path start = Paths.get(argsName.get("path"));
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toFile().getAbsoluteFile()));
        }
        if (start.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("File is directory %s", start.toFile().getAbsoluteFile()));
        }
        StringBuilder rsl = new StringBuilder();
        List<String> filter = new ArrayList<>(List.of(argsName.get("filter").split(",")));
        List<Integer> indexes = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        try (Scanner scanner = new Scanner(start)) {
            if (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(delimiter);
                for (int i = 0; i < line.length; i++) {
                    if (filter.contains(line[i])) {
                        indexes.add(i);
                        rsl.append(line[i]);
                        rsl.append(delimiter);
                    }
                }
            }
            while (scanner.hasNextLine()) {
                rsl.append(System.lineSeparator());
                String[] line = scanner.nextLine().split(delimiter);
                for (Integer index : indexes) {
                    rsl.append(line[index]);
                    rsl.append(delimiter);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String out = argsName.get("out");
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
