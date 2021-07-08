package ru.job4j.ood.lsp.parking;


public interface Place {

    boolean takePlace(Car car, int place);
    Car takeCar(int place);
}
