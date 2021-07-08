package ru.job4j.ood.lsp.parking;

public class TruckPlace implements Place {

    private final Car[] cars;

    public TruckPlace(int capacity) {
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
