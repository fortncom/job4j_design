package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
           throw new IllegalArgumentException("args is empty");
        }
        for (String arg : args) {
            if (arg.length() != 0) {
                String[] argument = arg.split("=");
                if (argument.length != 2) {
                    throw new IllegalArgumentException("Args not valid. Use skeleton of key=value");
                } else {
                    values.put(argument[0], argument[1]);
                }
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"Xmx=512", "encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"out=project.zip", "encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
