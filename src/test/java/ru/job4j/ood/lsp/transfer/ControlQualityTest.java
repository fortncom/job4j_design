package ru.job4j.ood.lsp.transfer;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ControlQualityTest {

    @Test
    public void whenFoodExpiredThenTrash() {
        Tomato tomato = new Tomato("Tomatof",
                LocalDateTime.of(2021, 4, 20, 18, 0),
                LocalDateTime.of(2021, 3, 7, 10, 0),
                150, 50);
        Trash trash = new Trash();
        ControlQuality qual = new ControlQuality(List.of(new Warehouse(), new Shop(), trash));
        qual.quality(tomato);
        List<Food> foodList = trash.findBy(food -> true);
        assertThat(foodList.get(0), is(tomato));
    }

    @Test
    public void whenFreshFoodMore75PercentThenTrash() {
        Chicken chicken = new Chicken("IndiL",
                LocalDateTime.of(3021, 6, 15, 23, 0),
                LocalDateTime.of(2020, 5, 15, 5, 0),
                245, 100);
        Warehouse warehouse = new Warehouse();
        ControlQuality qual = new ControlQuality(List.of(warehouse, new Shop(), new Trash()));
        qual.quality(chicken);
        List<Food> foodList = warehouse.findBy(food -> true);
        assertThat(foodList.get(0), is(chicken));
    }

    @Test
    public void whenFreshFoodLess25PercentThenDiscountShop() {
        int price = 245;
        int discount = 80;
        Bread bread = new Bread("french Buguette",
                LocalDateTime.of(2121, 7, 8, 12, 0),
                LocalDateTime.of(1021, 7, 5, 12, 0),
                price, discount);
        Shop shop = new Shop();
        ControlQuality qual = new ControlQuality(List.of(new Warehouse(), shop, new Trash()));
        qual.quality(bread);
        List<Food> foodList = shop.findBy(food -> true);
        int rsl = foodList.get(0).price;
        int expected = price - discount;
        assertEquals(expected, rsl);
    }
}