package ru.job4j.ood.srp.disturbance;

/**
 * Данный интерфейс нарушает принцип SRP тем, что обьявляет
 * метод transportation(String place), который нужно выделить
 * в отдельный интерфейс. Данный метод описывает функционал
 * присущий интерфейсу логистики и не имеющий отношения к
 * к продуктам.
 *
 */

public interface Product {

    void changeName(String name);
    void setPrice(int price);
    boolean transportation(String place);

}
