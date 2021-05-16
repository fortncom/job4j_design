package ru.job4j.io.searchby;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class FileFinder {

    private Path startDir;
    private String searchArg;
    private String target;
    private boolean rsl;

    private boolean findByName() throws IOException {
        List<Path> paths = search(path -> path.toFile().getName().equals(searchArg));
        return write(paths);
    }

    private boolean findByRegex() throws IOException {
        List<Path> paths = search(path -> path.toFile().getName().matches(searchArg));
        return write(paths);
    }

    private boolean findByMask() throws IOException {
        MaskFilter maskFilter = new MaskFilter(searchArg);
        List<Path> paths = search(path -> maskFilter.accept(path.toFile(), path.toFile().getName()));
        return write(paths);
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
                out.write(path.toString() + System.lineSeparator());
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
        ff.rsl = switch (args[2].toLowerCase()) {
            case "file" ->  ff.findByName();
            case "mask" -> ff.findByMask();
            case "regex" -> ff.findByRegex();
            default -> throw new IllegalArgumentException("Invalid data type specified.".concat(
                    " Available argument \"file\" or \"mask\" or \"regex\"."));
        };
        System.out.println(String.format("Запись в файл выполнена: %b", ff.rsl));
    }
}
