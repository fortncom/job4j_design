package ru.job4j.ood.ocp.disturbance;

/**
 * Интерфейс Shop нарушает принцип OCP тем, что метод
 * sell(Arabica arabica) в принимаемом параметре
 * использует конечную реализацию, вместо
 * использования абстракции в виде типа Product.
 *
 */

public interface Shop {

    boolean sell(Arabica arabica);

    interface Product {
       String name(int index);
    }

    final class Arabica implements Product {

        String[] storage = new String[100];

        @Override
        public String name(int index) {
            return storage[index];
        }
    }
}
