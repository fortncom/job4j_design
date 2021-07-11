package ru.job4j.ood.dip.disturbance;

/**
 * Конструктор юзера нарушает DIP, сужая диапазон поля browser,
 * до реализации Chrome.
 *
 */

public class User {

    private final String name;
    private final Browser browser;

    public User(String name, Chrome browser) {
        this.name = name;
        this.browser = browser;
    }
}

interface Browser {
    void browse();
}

class Chrome implements Browser {

    @Override
    public void browse() {
        //logic
    }
}