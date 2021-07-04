package ru.job4j.ood.ocp.disturbance;

import ru.job4j.ood.srp.Employee;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс Store нарушает принцип OCP тем, что метод findBy()
 * объявлен с типом Employee, хотя мог быть менее конкретным и
 * не использовать тип Employee на входе и выходе.
 *
 */

public interface Store {

    List<Employee> findBy(Predicate<Employee> filter);

}