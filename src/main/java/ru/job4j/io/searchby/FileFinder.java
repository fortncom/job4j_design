package ru.job4j.io.searchby;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileFinder {

    private Path startDir;
    private String searchArg;
    private String target;
    String type;
    private boolean rsl;

    private boolean find() throws IOException {
        List<Path> paths = search(condition());
        return write(paths);
    }

    private Predicate<Path> condition() {
        if (type.equals("file")) {
           return path -> path.toFile().getName().equals(searchArg);
        } else if (type.equals("regex")) {
           return path -> Pattern.compile(searchArg).matcher(path.toFile().getName()).matches();
        } else if (type.equals("mask")) {
            searchArg = searchArg.replace("?", ".?").replace("*", ".*");
            return path -> Pattern.compile(searchArg).matcher(path.toFile().getName()).matches();
        }
        throw new IllegalArgumentException("Invalid data type specified.".concat(
                " Available argument \"file\" or \"mask\" or \"regex\"."));
    }

    private List<Path> search(Predicate<Path> condition) throws IOException {
        SelectorVisitor visitor = new SelectorVisitor(condition);
        Files.walkFileTree(startDir, visitor);
        return visitor.getPaths();
    }

    private boolean write(List<Path> paths) {
        if (paths.isEmpty()) {
            return false;
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(target, false))) {
            for (Path path : paths) {
                out.write(path.toString());
                out.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException(
                "Insufficient number of input parameters. ".concat(
                        "Usage java -jar dir.jar ROOT_FOLDER ").concat(
                        "SEARCH_PARAMETER ").concat(
                        "TYPE(FILE_OR_REGEX_OR_MASK) ").concat(
                        "TARGET_FOLDER"));
        }
        FileFinder ff = new FileFinder();
        ff.startDir = Paths.get(args[0]);
        if (!ff.startDir.toFile().exists()) {
            throw new IllegalArgumentException(String.format(
                    "Not exist %s", ff.startDir.toFile().getAbsoluteFile()));
        }
        if (ff.startDir.toFile().isFile()) {
            throw new IllegalArgumentException(String.format(
                    "Not directory %s", ff.startDir.toFile().getAbsoluteFile()));
        }
        ff.searchArg = args[1];
        ff.target = args[3];
        ff.type = args[2].toLowerCase();
        ff.rsl = ff.find();
        System.out.println(String.format("Запись в файл выполнена: %b", ff.rsl));
    }
}
