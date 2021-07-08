package ru.job4j.ood.lsp.parking;

public class LightPlace implements Place {

    private final Car[] cars;

    public LightPlace(int capacity) {
        cars = new Car[capacity];
    }

    @Override
    public boolean takePlace(Car car, int place) {
        if (cars[place] != null) {
            throw new IllegalArgumentException("This place is occupied");
        }
        if (car.getSize() == 1) {
            for (int i = 0; i < cars.length; i++) {
                if (cars[i] == null) {
                    cars[i] = car;
                }
            }
        } else {
            int count = 0;
            int size = car.getSize();
            for (int i = 0; i < cars.length; i++) {
                if (cars[i] == null) {
                    count++;
                } else {
                    count = 0;
                }
                if (size == count) {
                    for (int j = i; j > j - size; j--) {
                        cars[j] = car;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Car takeCar(int place) {
        Car car = cars[place];
        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }
        int size = car.getSize();
        cars[place] = null;
        if (size != 1) {
            for (int i = place - size; i < place + size; i++) {
                if (i < 0 || i > cars.length) {
                    continue;
                }
                if (cars[i] != null && cars[i].equals(car)) {
                    cars[i] = null;
                }
            }
        }
        return car;
    }

}
