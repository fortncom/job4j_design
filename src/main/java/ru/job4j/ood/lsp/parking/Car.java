package ru.job4j.ood.lsp.parking;

import java.util.Objects;

class Car {

    private int size;
    private boolean stand = false;

    public Car(SizeCar size) {
        this.size = size.getValue();
    }

    public boolean isStand() {
        return stand;
    }

    public void setStand(boolean stand) {
        this.stand = stand;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Car{"
                + "size=" + size
                + ", stand=" + stand
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
        return size == car.size
                && stand == car.stand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, stand);
    }
}
