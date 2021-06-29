package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    private <T> T search(List<T> value, BiPredicate<T, T> predicate) {
        T rsl = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (predicate.test(rsl, value.get(i))) {
                rsl = value.get(i);
            }
        }
        return rsl;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return search(value, (t1, t2) -> comparator.compare(t1, t2) < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return search(value, (t1, t2) -> comparator.compare(t1, t2) > 0);
    }
}
