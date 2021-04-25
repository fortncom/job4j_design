package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] data;
    private int count = 0;

    public SimpleArray(T[] data, int size) {
        this.data = Arrays.copyOf(data, size);
        count = data.length;
    }

    public void add(T model) {
        for (int i = 0; i < count; i++) {
            if (data[i] == null) {
                set(i, model);
                return;
            }
        }
        if (count < data.length) {
            data[count++] = model;
        }
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, count - 1);
        data[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, count - 1);
        System.arraycopy(data, index + 1, data, index, count - 1 - index);
        data[count - 1] = null;
        count--;
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < count;
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
