package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuUI {

    private final Output out;

    public MenuUI(Output out) {
        this.out = out;
    }

    public void start(Input input, Menu menu) {
        boolean run = true;
        while (run) {
            this.showItems(menu.findAll());
            String msg = "Select: ";
            String select = input.ask(msg);
            if (select.equals("exit")) {
                run = false;
                continue;
            }
            Item item = menu.findItem(select);
            if (item == null) {
                out.println("Wrong input, try again.");
                continue;
            }
            Action action = item.getAction();
            if (action != null) {
                run = action.execute(input, item);
            }
        }
    }

    private void showItems(List<Item> items) {
        StringBuilder rsl = new StringBuilder();
        for (Item item : items) {
            rsl.append(item.getName());
            List<Item> que = new ArrayList<>(item.getChildren());
            while (!que.isEmpty()) {
                Item item1 = que.get(0);
                que.remove(0);
                rsl.append(System.lineSeparator());
                int count = item1.getName().split("\\.").length;
                rsl.append("----".repeat(Math.max(0, count - 1)));
                rsl.append(item1.getName());
                List<Item> children = new ArrayList<>(item1.getChildren());
                for (int i = children.size() - 1; i >= 0; i--) {
                    que.add(0, children.get(i));
                }
            }
            rsl.append(System.lineSeparator());
        }
        out.println(rsl.toString());
    }

}
