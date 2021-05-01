package ru.job4j.collection.map;

import java.util.*;

public class User {

    public static void main(String[] args) {
        Calendar date = new
                GregorianCalendar(1972, 12, 21, 13, 22, 22);
        User user = new User("Tom", 1, date);
        User user2 = new User("Tom", 1, date);
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

    @SuppressWarnings("CheckStyle")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday);
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
