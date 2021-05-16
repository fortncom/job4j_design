package ru.job4j.io.searchby;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SelectorVisitor extends SimpleFileVisitor<Path> {

    private List<Path> paths = new ArrayList<>();
    private Predicate<Path> condition;

    public SelectorVisitor(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            paths.add(file);
        }
        return CONTINUE;
    }
}
