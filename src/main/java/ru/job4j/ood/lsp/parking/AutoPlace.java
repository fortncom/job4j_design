package ru.job4j.ood.lsp.parking;

public class AutoPlace implements Place {

    private final Car[] cars;

    public AutoPlace(int capacity) {
        cars = new Car[capacity];
    }

    @Override
    public boolean takePlace(Car car, int place) {
        boolean rsl = false;
        if (cars[place] != null) {
            throw new IllegalArgumentException("This place is occupied");
        }
        if (car.getSize() == 1) {
            cars[place] = car;
            rsl = true;
        } else {
            int count = 0;
            int size = car.getSize();
            for (int i = place - size; i < place + size; i++) {
                if (i < 0 || i > cars.length) {
                    continue;
                }
                count = cars[i] == null ? count + 1 : 0;
                if (size == count) {
                    for (int j = i; j > i - size; j--) {
                        cars[j] = car;
                    }
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
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
