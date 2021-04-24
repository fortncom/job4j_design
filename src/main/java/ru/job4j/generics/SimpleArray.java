package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] data;

    public SimpleArray(T[] data, int size) {
        data = Arrays.copyOf(data, size);
        this.data = data;
    }

    public void add(T model) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                set(i, model);
                return;
            }
        }
//        data = Arrays.copyOf(this.data, this.data.length + 1);
//        set(data.length - 1, model);
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, data.length);
        data[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, data.length);
        System.arraycopy(data, index + 1, data, index, data.length - 1 - index);
        data[data.length - 1] = null;
    }

    public T get(int index) {
        return data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < data.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[cursor++];
            }
        };
    }
}
