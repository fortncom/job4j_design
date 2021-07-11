package ru.job4j.ood.dip.disturbance;

/**
 * Метод translate() нарушает DIP принцип используя в качестве типа
 * ридера тип его реализации.
 *
 */

public class Translator {

    String translate(String text) {
        String rsl = "";
        FileReader reader = new FileReader();
        //logic
        return rsl;
    }

}

interface Reader {

    String read();
}

class FileReader implements Reader {

    @Override
    public String read() {
        return null;
    }
}