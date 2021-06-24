package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {

    private AbstractCache<String, String> cache;
    private Scanner scanner = new Scanner(System.in);

    public Emulator(AbstractCache<String, String> cache) {
        this.cache = cache;
    }

    public void init() {
        boolean run = true;
        while (run) {
            System.out.println(
                    "Enter the file name to get the file or 'out' to exit"
                    .concat(System.lineSeparator()).concat("Enter: "));
            String key = scanner.nextLine();
            if (key.equals("out")) {
                System.exit(1);
            }
            String value = cache.get(key);
            if (value != null) {
                System.out.println(value);
            }
        }
    }

    public static void main(String[] args) {
        DirFileCache cache = new DirFileCache("src/main/resources");
        Emulator emulator = new Emulator(cache);
        emulator.init();
    }

}
