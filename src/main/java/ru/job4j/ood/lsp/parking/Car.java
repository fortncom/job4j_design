package ru.job4j.ood.lsp.parking;

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

}
