package ru.job4j.ood.lsp.transfer;

import java.time.Duration;
import java.time.LocalDateTime;

public class ControlQuality {

    protected Store warehouse;
    protected Store shop;
    protected Store trash;

    public ControlQuality(Store warehouse, Store shop, Store trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void quality(Food food) {
        Store store;
        LocalDateTime currentDate = LocalDateTime.now();
        Duration fullPeriod = Duration.between(food.createDate, food.expiryDate);
        Duration remaining = Duration.between(currentDate, food.expiryDate);
        double freshPercent = remaining.toHours() / (fullPeriod.toHours() * 0.01);
        if (freshPercent < 0) {
            store = trash;
        } else if (freshPercent > 75) {
            store = warehouse;
        } else {
            if (freshPercent < 25) {
                food.price = food.price - food.discount;
            }
            store = shop;
        }
        store.add(food);
    }
}
