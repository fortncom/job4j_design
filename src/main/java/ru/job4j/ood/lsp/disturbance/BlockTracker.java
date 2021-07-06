package ru.job4j.ood.lsp.disturbance;

/**
 * Подкласс AutoBlockTracker меняет постусловие выходного параметра
 * тем самым нарушая принцип LSP.
 *
 *
 */

public class BlockTracker {

    protected Location locationBinding;
    protected boolean block = false;
    protected int distance = 1000;

    public BlockTracker(Location locationBinding) {
        this.locationBinding = locationBinding;
    }

    public Location track() {
        Location currentLocation = new Location();
        //logic
        if (currentLocation.latitude > locationBinding.latitude + distance) {
            block = true;
            currentLocation.latitude = -200;
            currentLocation.longitude = -200;
        }
        if (currentLocation.longitude > locationBinding.longitude + distance) {
            block = true;
            currentLocation.latitude = -200;
            currentLocation.longitude = -200;
        }
        return currentLocation;
    }
}

class AutoBlockTracker extends BlockTracker {

    public AutoBlockTracker(Location locationBinding) {
        super(locationBinding);
    }

    @Override
    public Location track() {
        Location currentLocation = new Location();
        //logic
        if (currentLocation.latitude > locationBinding.latitude + distance + 100) {
            block = true;
        }
        if (currentLocation.longitude > locationBinding.longitude + distance + 100) {
            block = true;
        }
        return currentLocation;
    }
}

class Location {

    protected double latitude;
    protected double longitude;

    public Location() {
    }

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}