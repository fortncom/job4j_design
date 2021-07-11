package ru.job4j.ood.isp.menu;

public interface Output {

    void println(Object obj);
}

class ConsoleOutput implements Output {

    @Override
    public void println(Object s) {
        System.out.println(s);
    }
}

class StubOutput implements Output {

    StringBuilder builder = new StringBuilder();

    @Override
    public void println(Object s) {
        builder.append(s);
        builder.append(System.lineSeparator());
    }

    public StringBuilder getBuilder() {
        return builder;
    }
}