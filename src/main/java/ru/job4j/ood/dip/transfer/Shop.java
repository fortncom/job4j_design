package ru.job4j.ood.dip.transfer;

import java.time.Duration;
import java.time.LocalDateTime;
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
    public boolean accept(Food food) {
        boolean rsl = false;
        Duration fullPeriod = Duration.between(food.createDate, food.expiryDate);
        Duration remaining = Duration.between(LocalDateTime.now(), food.expiryDate);
        double freshPercent = remaining.toHours() / (fullPeriod.toHours() * 0.01);
        if (freshPercent < 75 && freshPercent > 0) {
            rsl = true;
        }
        if (freshPercent < 25 && !food.isDiscount) {
            food.price = food.price - food.discount;
            food.isDiscount = true;
        }
        return rsl;
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return food.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public List<Food> giveBack() {
        return new ArrayList<>(food);
    }

    @Override
    public void clear() {
        food.clear();
    }
}
