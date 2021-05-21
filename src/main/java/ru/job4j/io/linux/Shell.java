package ru.job4j.io.linux;

import java.nio.file.Path;

public class Shell {

    private Path currentPath = Path.of("/");
    private StringBuilder rsl = new StringBuilder();

    public void cd(String path) {
        currentPath = currentPath.resolve(path).normalize();
    }

    public String pwd() {
        rsl.setLength(0);
        Path fileName = currentPath.getFileName();
        if (fileName != null) {
            for (Path path : currentPath) {
                rsl.append("/").append(path);
            }
        } else {
            rsl.append("/");
        }
        return rsl.toString();
    }
}
