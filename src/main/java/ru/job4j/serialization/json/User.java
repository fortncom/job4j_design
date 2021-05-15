package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public boolean isMarried() {
        return married;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String[] getChildren() {
        return children;
    }

    public Location getLocation() {
        return location;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return married == user.married
                && age == user.age
                && Objects.equals(name, user.name)
                && Arrays.equals(children, user.children)
                && Objects.equals(location, user.location);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(married, name, age, location);
        result = 31 * result + Arrays.hashCode(children);
        return result;
    }

    public static void main(String[] args) {
        Location location = new Location(50.0, 50.0);
        User user = new User(true, "Pol", 28,
                new String[]{"Tom", "Wilson"}, location);

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(user));

        System.out.println(new JSONObject(user).toString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("married", user.isMarried());
        jsonObject.put("name", user.getName());
        jsonObject.put("age", user.getAge());
        jsonObject.put("location", new JSONObject(
                "{\"latitude\":\"50.0\",\"longitude\":\"50.0\"}"));
        jsonObject.put("children", new JSONArray(
                new ArrayList<>(List.of("Tom", "Wilson"))));
        String jsonUser = jsonObject.toString();
        final User userCopy = gson.fromJson(jsonUser, User.class);
        System.out.println(user.equals(userCopy));
    }
}
