package ru.job4j.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.getId(), user);
        }
        for (User user : current) {
            User user2 = map.get(user.getId());
            if (user2 == null) {
                info.added++;
                continue;
            } else if (!user.getName().equals(user2.getName())) {
                info.changed++;
            }
            map.remove(user2.getId());
        }
        info.deleted = map.size();
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
