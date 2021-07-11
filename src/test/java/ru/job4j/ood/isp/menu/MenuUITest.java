package ru.job4j.ood.isp.menu;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

public class MenuUITest {

    @Test
    public void start() {
        Input input = new StubInput(new String[]{"Задача 1", "exit"});
        StubOutput output = new StubOutput();
        Action action = new DemoAction(output);
        ItemMenu menu = new ItemMenu(Comparator.comparing(Item::getName));
        Item item = new Item("Задача 1");
        Item item2 = new Item("Задача 2");
        Item item3 = new Item("Задача 1.1");
        Item item4 = new Item("Задача 1.1.1");
        item.setText("Создать меню.");
        item.setAction(action);
        item.getChildren().add(item3);
        item3.getChildren().add(item4);
        menu.add(item);
        menu.add(item2);
        MenuUI ui = new MenuUI(output);
        ui.start(input, menu);
        String rsl = output.getBuilder().toString();
        StringBuilder expected = new StringBuilder();
        expected.append("Задача 1").append(System.lineSeparator());
        expected.append("----Задача 1.1").append(System.lineSeparator());
        expected.append("--------Задача 1.1.1").append(System.lineSeparator());
        expected.append("Задача 2").append(System.lineSeparator());
        expected.append(System.lineSeparator());
        expected.append("=== Triggered Demo Action ====").append(System.lineSeparator());
        expected.append("Создать меню.").append(System.lineSeparator());
        expected.append("Задача 1").append(System.lineSeparator());
        expected.append("----Задача 1.1").append(System.lineSeparator());
        expected.append("--------Задача 1.1.1").append(System.lineSeparator());
        expected.append("Задача 2").append(System.lineSeparator());
        expected.append(System.lineSeparator());
        Assert.assertThat(rsl, Is.is(expected.toString()));
    }

}