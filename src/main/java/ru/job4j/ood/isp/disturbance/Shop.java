package ru.job4j.ood.isp.disturbance;

/**
 * Реализация CraftShop не использует метод .sale() и
 * это нарушает принцип ISP.
 *
 */

public interface Shop {

    void sell(String product);
    void sale();
}



class Achan implements Shop {

    @Override
    public void sell(String product) {
    }

    @Override
    public void sale() {
    }
}

class CraftShop implements Shop {

    @Override
    public void sell(String product) {
    }

    @Override
    public void sale() {
       throw new UnsupportedOperationException();
    }
}