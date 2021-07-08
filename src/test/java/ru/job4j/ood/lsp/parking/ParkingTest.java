package ru.job4j.ood.lsp.parking;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void whenParkingLightCarThenLightPlace() {
        Car car = new Car(SizeCar.ONE_PLACE);
        Place light = new LightPlace(100);
        Place truck = new TruckPlace(50);
        Parking parking = new CarParking(List.of(light, truck));
        parking.park(car, 1);
        assertThat(light.takeCar(1), Is.is(car));
    }

    @Test
    public void whenParkingTruckCarThenTruckPlace() {
        Car car = new Car(SizeCar.THREE_PLACE);
        Place light = new LightPlace(100);
        Place truck = new TruckPlace(50);
        Parking parking = new CarParking(List.of(light, truck));
        parking.park(car, 1);
        assertThat(truck.takeCar(1), Is.is(car));
    }

    @Test
    public void whenParkingTruckCarToLightPlaceThenTrue() {
        Car car = new Car(SizeCar.THREE_PLACE);
        Place light = new LightPlace(100);
        Parking parking = new CarParking(List.of(light));
        boolean rsl = parking.park(car, 1);
        assertThat(rsl, Is.is(Boolean.TRUE));
    }

}