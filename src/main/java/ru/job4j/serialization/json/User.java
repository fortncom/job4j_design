package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public static void main(String[] args) {
        Location location = new Location(50.0, 50.0);
        User user = new User(true, "Pol", 28,
                new String[]{"Tom", "Wilson"}, location);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(user));

        final String userJson = "{\"married\":true,"
                + "\"name\":\"Pol\","
                + "\"age\":28,"
                + "\"children\":[\"Tom\",\"Wilson\"],"
                + "\"location\":{\"latitude\":50.0,\"longitude\":50.0}}";

        final User userMod = gson.fromJson(userJson, User.class);
        System.out.println(userMod);
    }
}
