package ru.job4j.ood.lsp.parking;

import java.util.Objects;

abstract class Car {

    private int size;

    public Car(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Car{"
                + "size=" + size
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return size == car.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
