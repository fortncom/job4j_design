package ru.job4j.mail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddAction implements UserAction {

    private final Output out;

    public AddAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new User";
    }

    @Override
    public boolean execute(Input input, Base base) {
        out.println("=== Add a new User ====");
        String name = input.askStr("Enter name: ");
        List<String> mails = Arrays.stream(input.askStr("Enter mail/mails separated by commas: ")
                .split(",")).map(String::trim).collect(Collectors.toList());
        User user = new User(name, mails);
        base.add(user);
        out.println("Все доступные пользователи:");
        for (User user1 : base.findAll()) {
            out.println(user1);
        }
        return true;
    }

}
