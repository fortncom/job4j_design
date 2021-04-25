package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        T t = findById(id);
        if (t != null) {
            mem.set(mem.indexOf(t), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        T t = findById(id);
        if (t != null) {
            mem.remove(t);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T t : mem) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }
}
