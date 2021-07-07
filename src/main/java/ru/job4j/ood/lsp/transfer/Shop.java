package ru.job4j.ood.lsp.transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Shop implements Store {

    private final List<Food> food = new ArrayList<>();

    public void add(Food f) {
        food.add(f);
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return food.stream().filter(filter).collect(Collectors.toList());
    }
}
