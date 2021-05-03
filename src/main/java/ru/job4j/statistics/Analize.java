package ru.job4j.statistics;

import java.util.List;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        for (User user : current) {
           if (!previous.contains(user)) {
               info.added++;
           }
        }
        for (User user : previous) {
            if (!current.contains(user)) {
                info.deleted++;
            } else {
                if (!user.getName().equals(current.get(current.indexOf(user)).getName())) {
                    info.changed++;
                }
            }
        }
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            User user = (User) o;
            return getId() == (user.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId());
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
