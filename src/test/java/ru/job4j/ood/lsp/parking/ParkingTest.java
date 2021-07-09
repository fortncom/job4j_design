package ru.job4j.ood.lsp.parking;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenParkingAutoThenAutoPlace() {
        Car car = new Auto(1);
        Place autoPlace = new AutoPlace(100);
        Place truckPlace = new TruckPlace(50);
        Parking parking = new CarParking(List.of(autoPlace, truckPlace));
        parking.park(car, 1);
        assertThat(autoPlace.takeCar(1), Is.is(car));
    }

    @Test
    public void whenParkingTruckCarThenTruckPlace() {
        Car car = new Truck(3);
        Place autoPlace = new AutoPlace(100);
        Place truckPlace = new TruckPlace(50);
        Parking parking = new CarParking(List.of(truckPlace, autoPlace));
        parking.park(car, 1);
        assertThat(truckPlace.takeCar(1), Is.is(car));
    }

    @Test
    public void whenParkingTruckCarToLightPlaceThenTrue() {
        Car car = new Truck(3);
        Place autoPlace = new AutoPlace(100);
        Parking parking = new CarParking(List.of(autoPlace));
        boolean rsl = parking.park(car, 1);
        assertThat(rsl, Is.is(Boolean.TRUE));
    }

}