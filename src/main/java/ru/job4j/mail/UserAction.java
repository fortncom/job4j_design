package ru.job4j.mail;

public interface UserAction {
    String name();

    boolean execute(Input input, Base base);
}
