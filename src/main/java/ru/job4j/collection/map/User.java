package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    public static void main(String[] args) {
        User user = new User("Tom", 1,
                new GregorianCalendar(1972, 12, 21));

        User user2 = new User("Tom", 1,
                new GregorianCalendar(1972, 12, 21));
        Map<User, Object> map = new HashMap<>();
        map.put(user, new Object());
        map.put(user2, new Object());
        for (Map.Entry<User, Object> userObjectEntry : map.entrySet()) {
            System.out.println(userObjectEntry);
        }
    }

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }
}
