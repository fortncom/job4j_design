package ru.job4j.ood.dip.disturbance;


/**
 * Метод takePlane() интерфейса Airport нарушает принцип DIP тем,
 * что принимает на вход реализацию, вместо абстракции.
 *
 */

public interface Airport {
    void takePlane(AirbusA320 a320);
}

interface Plane {
    void fly();
}

class AirbusA320 implements Plane {

    @Override
    public void fly() {
        //logic
    }
}
