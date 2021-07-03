package ru.job4j.ood.srp.disturbance;

public interface Transport {

    void go();
    void setPassengers(int passengers);
    double refuel(int fuel);
}
