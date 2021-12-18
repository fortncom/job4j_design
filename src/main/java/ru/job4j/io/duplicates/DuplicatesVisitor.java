package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Path> allFiles = new HashMap<>();
    private List<FileProperty> duplicate = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (allFiles.containsKey(fileProperty)) {
            Path path = allFiles.get(fileProperty);
            System.out.println(path.toAbsolutePath());
            duplicate.add(fileProperty);
            System.out.println("Duplicate : " + file.getFileName().toAbsolutePath());
        } else {
            allFiles.put(fileProperty, file);
        }
        return super.visitFile(file, attrs);
    }
}
