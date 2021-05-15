package ru.job4j.serialization.xml;

import java.util.Arrays;

public class User {

    private final boolean married;
    private final String name;
    private final int age;
    private final String[] children;
    private final Location location;

    public User(boolean married, String name, int age, String[] children, Location location) {
        this.married = married;
        this.name = name;
        this.age = age;
        this.children = children;
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{"
                + "married=" + married
                + ", name='" + name + '\''
                + ", age=" + age
                + ", children=" + Arrays.toString(children)
                + ", location=" + location
                + '}';
    }
}
