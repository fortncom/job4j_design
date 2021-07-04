package ru.job4j.ood.srp.disturbance;

/**
 * Данный интерфейс нарушает принцип SRP тем, что обьявляет
 * методы setPassengers(int passengers) и refuel(int fuel),
 * которые описывают узко направленный функционал меняющийся
 * в зависимости от реализации. Такой интерфейс имеет более
 * одной причины для изменения.
 *
 */

public interface Transport {

    void go();
    void setPassengers(int passengers);
    double refuel(int fuel);
}
