package ru.job4j.ood.srp;

import java.sql.Connection;

public interface Store<T> {

    void add(T model);
    boolean delete(String id);
    Connection getDBConnection();
    void saveDB();
}