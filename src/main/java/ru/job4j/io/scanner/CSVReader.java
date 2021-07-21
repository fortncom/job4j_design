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

    private StringBuilder rsl = new StringBuilder();
    private ArgsName argsName;

    public CSVReader(ArgsName argsName) {
        this.argsName = argsName;
    }

    public ArgsName getArgsName() {
        return argsName;
    }

    public List<Integer> indicesOf(String[] data) {
        List<String> filter = new ArrayList<>(
                List.of(argsName.get("filter").split(",")));
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (filter.contains(data[i])) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public void parseLine(String[] line, List<Integer> indexes) {
        for (Integer index : indexes) {
            rsl.append(line[index]);
            rsl.append(argsName.get("delimiter"));
        }
        rsl.append(System.lineSeparator());
    }

    public void write() {
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

    public static void main(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Insufficient number of input parameters Usage java -jar dir.jar PATH DELIMITER OUT FILTER.");
        }
        CSVReader csv = new CSVReader(ArgsName.of(args));
        Path start = Paths.get(csv.getArgsName().get("path"));
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toFile().getAbsoluteFile()));
        }
        if (start.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("File is directory %s", start.toFile().getAbsoluteFile()));
        }
        List<Integer> indexes;
        String delimiter = csv.getArgsName().get("delimiter");
        try (Scanner scanner = new Scanner(start)) {
            if (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(delimiter);
                indexes = csv.indicesOf(line);
                csv.parseLine(line, indexes);
            }
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(delimiter);
                indexes = csv.indicesOf(line);
                csv.parseLine(line, indexes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        csv.write();
    }
}
