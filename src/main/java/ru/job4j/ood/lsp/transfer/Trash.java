package ru.job4j.ood.lsp.transfer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Trash implements Store {

    private final List<Food> food = new ArrayList<>();

    public void add(Food f) {
        food.add(f);
    }

    @Override
    public boolean accept(Food food) {
        boolean rsl = false;
        if (Duration.between(LocalDateTime.now(), food.expiryDate).toHours() < 0) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return food.stream().filter(filter).collect(Collectors.toList());
    }
}
