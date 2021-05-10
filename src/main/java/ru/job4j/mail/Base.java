package ru.job4j.mail;

import java.util.*;

public class Base {

    private final Map<String, User> allUsers = new HashMap<>();

    public void add(User user) {
        for (String userMail : user.getMails()) {
            if (allUsers.containsKey(userMail)) {
                User mainUser = allUsers.get(userMail);
                for (String s : user.getMails()) {
                    allUsers.put(s, mainUser);
                    mainUser.getMails().add(s);
                }
                return;
            }
        }
        for (String s : user.getMails()) {
            allUsers.put(s, user);
        }
    }

    public boolean delete(String name) {
        int size = allUsers.size();
        List<String> usersDelete = new ArrayList<>();
        for (Map.Entry<String, User> entry : allUsers.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                usersDelete.add(entry.getKey());
            }
        }
        if (!usersDelete.isEmpty()) {
            for (String s : usersDelete) {
                allUsers.remove(s);
            }
        }
        return size != allUsers.size();
    }

    public List<User> findAll() {
        return new ArrayList<>(
                new HashSet<>(allUsers.values()));
    }
}
