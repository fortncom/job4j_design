package ru.job4j.ood.dip.transfer;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void add(Food food);
    boolean accept(Food food);
    List<Food> findBy(Predicate<Food> filter);
    List<Food> giveBack();
    void clear();

}
