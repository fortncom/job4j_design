package ru.job4j.mail;

import java.util.ArrayList;
import java.util.List;

public class StartUI {

    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Base base, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            String msg = "Select: ";
            int select = input.askInt(msg);
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, base);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        Base base = new Base();
        List<UserAction> actions = new ArrayList<>(List.of(
                new AddAction(output),
                new DeleteAction(output),
                new ExitAction(output)));
        new StartUI(output).init(input, base, actions);
    }

}
