package ru.job4j.ood.isp.disturbance;

/**
 * Нарушение данного принципа зависит от контекста применения.
 * Метод .sail() интерфейса Ship характерен не для всех
 * реализаций и тем самым нарушает ISP.
 *
 */

public interface Ship {

    void move(int distance);
    void sail();
}



class Yacht implements Ship {

    @Override
    public void move(int distance) {
    }

    @Override
    public void sail() {
    }
}

class Liner implements Ship {

    @Override
    public void move(int distance) {
    }

    @Override
    public void sail() {
    }
}