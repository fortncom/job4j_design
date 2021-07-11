package ru.job4j.ood.isp.menu;

import java.util.*;

public interface Menu {

    void add(Item item);
    List<Item> findAll();
    Item findItem(String name);
}

class ItemMenu implements Menu {

    private final TreeSet<Item> treeSet;

    public ItemMenu(Comparator<Item> comparator) {
        treeSet = new TreeSet<>(comparator);
    }

    @Override
    public void add(Item item) {
        treeSet.add(item);
    }

    @Override
    public List<Item> findAll() {
        return List.copyOf(treeSet);
    }

    @Override
    public Item findItem(String name) {
        Item rsl = null;
        List<Item> que = new ArrayList<>(treeSet);
        while (!que.isEmpty()) {
            Item item1 = que.get(0);
            que.remove(0);
            if (item1.getName().equals(name)) {
                rsl = item1;
                break;
            }
            que.addAll(item1.getChildren());
        }
        return rsl;
    }

}