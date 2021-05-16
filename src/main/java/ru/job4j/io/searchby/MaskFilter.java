package ru.job4j.io.searchby;

import java.io.File;
import java.io.FilenameFilter;

public class MaskFilter implements FilenameFilter {

    String mask;

    MaskFilter(String mask) {
        this.mask = mask;
    }

    @Override
    public boolean accept(File file, String name) {
        if (mask.equals("*")) {
            return true;
        } else if (mask.equals("*dir")) {
            return file.isDirectory();
        } else {
            return name.endsWith(mask);
        }
    }
}
