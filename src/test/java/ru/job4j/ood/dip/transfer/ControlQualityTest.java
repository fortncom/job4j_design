package ru.job4j.ood.dip.transfer;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ControlQualityTest {

    @Test
    public void whenSortThenListEmpty() {
        Bread bread = new Bread("french Buguette",
                LocalDateTime.of(2121, 7, 8, 12, 0),
                LocalDateTime.of(1021, 7, 5, 12, 0),
                55, 20);
        Shop shop = new Shop();
        ControlQuality cq = new ControlQuality(List.of(new Warehouse(), shop, new Trash()),
                new ArrayList<>(List.of(bread)));
        cq.sort();
        boolean rsl = cq.foods.isEmpty();
        assertThat(rsl, Is.is(Boolean.TRUE));
    }

    @Test
    public void whenSortThenFoodDiscountTrue() {
        Bread bread = new Bread("french Buguette",
                LocalDateTime.of(2121, 7, 8, 12, 0),
                LocalDateTime.of(1021, 7, 5, 12, 0),
                55, 20);
        Shop shop = new Shop();
        ControlQuality cq = new ControlQuality(List.of(new Warehouse(), shop, new Trash()),
                new ArrayList<>(List.of(bread)));
        cq.sort();
        boolean rsl = bread.isDiscount();
        assertThat(rsl, Is.is(Boolean.TRUE));
    }

    @Test
    public void resort() {
        Chicken chicken = new Chicken("IndiL",
                LocalDateTime.of(3021, 6, 15, 23, 0),
                LocalDateTime.of(2020, 5, 15, 5, 0),
                245, 100);
        Warehouse warehouse = new Warehouse();
        ControlQuality cq = new ControlQuality(List.of(warehouse, new Shop(), new Trash()),
                new ArrayList<>(List.of(chicken)));
        cq.sort();
        cq.resort();
        Food rsl = warehouse.giveBack().get(0);
        assertThat(rsl, Is.is(chicken));
    }

}