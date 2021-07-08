package ru.job4j.ood.lsp.parking;

import java.util.List;

public class CarParking implements Parking {

    private List<Place> placeList;

    public CarParking(List<Place> placeList) {
        this.placeList = placeList;
    }


    @Override
    public boolean park(Car car, int place) {
        return false;
    }

}


