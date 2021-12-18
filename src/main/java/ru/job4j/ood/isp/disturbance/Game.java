package ru.job4j.ood.isp.disturbance;

/**
 * Класс MMOSnake использует заглушку для метода .stop(),
 * поэтому этот метод для интерфейса Game избыточен и
 * нарушает принцип ISP.
 *
 */

public interface Game {
    void play();
    void stop();
    void replay();
}

class Snake implements Game {

    @Override
    public void play() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void replay() {
    }
}


class MMOSnake implements Game {

    @Override
    public void play() {
    }

    @Override
    public void stop() {
       throw new UnsupportedOperationException();
    }

    @Override
    public void replay() {
    }
}