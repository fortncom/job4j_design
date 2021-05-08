package ru.job4j.mail;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User {

    private String name;
    private Set<String> mails;

    public User(String name, List<String> mails) {
        this.name = name;
        this.mails = new HashSet<>(mails);
    }

    public String getName() {
        return name;
    }

    public Set<String> getMails() {
        return mails;
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
        return Objects.equals(name, user.name)
                && Objects.equals(mails, user.mails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mails);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", mails=" + mails
                + '}';
    }
}
