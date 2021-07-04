package ru.job4j.ood.srp.disturbance;

import java.sql.Connection;

/**
 * Данный интерфейс нарушает принцип SRP тем, что обьявляет
 * методы getDBConnection() и saveDB(T model) которые
 * более узко направленные и не являются общими для
 * всех реализаций Store.
 *
 */

public interface Store<T> {

    void add(T model);
    boolean delete(String id);
    Connection getDBConnection();
    void saveDB(T model);
}