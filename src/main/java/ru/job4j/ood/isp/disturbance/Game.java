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
        //logic
    }

    @Override
    public void stop() {
        //logic
    }

    @Override
    public void replay() {
        //logic
    }
}


class MMOSnake implements Game {

    @Override
    public void play() {
        //logic
    }

    @Override
    public void stop() {
       throw new UnsupportedOperationException();
    }

    @Override
    public void replay() {
        //logic
    }
}