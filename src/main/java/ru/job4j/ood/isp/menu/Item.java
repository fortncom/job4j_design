package ru.job4j.ood.isp.menu;

import java.util.Comparator;
import java.util.TreeSet;

public class Item {

    private final String name;
    private String text;
    private Action  action;
    private final TreeSet<Item> children = new TreeSet<>(Comparator.comparing(Item::getName));

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TreeSet<Item> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Item{"
                + "name='" + name + '\''
                + ", text='" + text + '\''
                + ", action=" + action
                + ", children=" + children
                + '}';
    }
}
