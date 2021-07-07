package ru.job4j.ood.lsp.parking;

public enum SizeCar {
    ONE_PLACE(1), TWO_PLACE(2), THREE_PLACE(3);

    private final int value;

    private SizeCar(int size) {
        this.value = size;
    }

    public int getValue() {
        return value;
    }
}
