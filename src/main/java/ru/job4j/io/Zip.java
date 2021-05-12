package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream copyFile = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(copyFile.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Insufficient number of input parameters Usage java -jar dir.jar ROOT_FOLDER EXCLUDE_EXTENSION TARGET_FOLDER.");
        }
        ArgsName argsName = ArgsName.of(args);

        Zip zip = new Zip();
        File start = new File(argsName.get("d"));
        String extension = argsName.get("e");
        if (!start.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.getAbsoluteFile()));
        }
        if (start.isFile() && !start.getName().endsWith(extension)) {
            zip.packSingleFile(start, new File(argsName.get("o")));
        } else {
            List<File> files = Search.search(start.toPath(),
                    p -> !(p.toFile().getName().endsWith(extension))).stream().map(Path::toFile).collect(Collectors.toList());
            zip.packFiles(files, new File(argsName.get("o")));
        }
    }
}
