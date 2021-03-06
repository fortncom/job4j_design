package ru.job4j.ood.lsp.parking;

public class TruckPlace implements Place {

    private final Car[] cars;

    public TruckPlace(int capacity) {
        cars = new Car[capacity];
    }

    @Override
    public boolean takePlace(Car car, int place) {
        if (cars[place] != null) {
            throw new IllegalArgumentException("This place is occupied");
        }
        cars[place] = car;
        return true;
    }

    @Override
    public Car takeCar(int place) {
        Car car = cars[place];
        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }
        return car;
    }
}
