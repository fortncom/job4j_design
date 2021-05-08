package ru.job4j.mail;

public class DeleteAction implements UserAction {

    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete User";
    }

    @Override
    public boolean execute(Input input, Base base) {
        out.println("=== Delete User ====");
        String name = input.askStr("Enter name: ").trim();
        if (base.delete(name)) {
            out.println("Пользователь удален успешно.");
        } else {
            out.println("Пользователь с таким именем отсутствует.");
        }
        out.println("Все доступные пользователи:");
        for (User user1 : base.findAll()) {
            out.println(user1);
        }
        return true;
    }
}
