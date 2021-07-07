package ru.job4j.ood.lsp.parking;

import java.util.Optional;

public interface Place {

    void takePlace(Car car);
    Optional<Integer> findFreePlace();
    boolean isRowFreePlace(int amount);
    Car takeCar(int place);
}
