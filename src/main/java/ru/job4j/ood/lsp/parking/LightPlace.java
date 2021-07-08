package ru.job4j.ood.lsp.parking;

public class LightPlace implements Place {

    private final Car[] cars;

    public LightPlace(int capacity) {
        cars = new Car[capacity];
    }

    @Override
    public boolean takePlace(Car car) {
        return false;
    }

    @Override
    public Car takeCar(int place) {
        return null;
    }

}
