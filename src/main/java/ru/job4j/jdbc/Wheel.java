package ru.job4j.jdbc;

public class Wheel {

    private int id;
    private int radius;

    public Wheel(int id, int radius) {
        this.id = id;
        this.radius = radius;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Wheel{"
                + "id=" + id
                + ", radius=" + radius
                + '}';
    }
}
