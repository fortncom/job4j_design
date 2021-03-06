package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Map<K, V>, Iterable<SimpleHashMap.Entry<K, V>> {

    private int capacity = 16;
    @SuppressWarnings("unchecked")
    private Entry<K, V>[] container = new Entry[capacity];
    private int load = (int) (capacity * 0.75);
    private int modCount = 0;
    int length = 0;

    @Override
    public boolean insert(K key, V value) {
        boolean rsl = false;
        Entry<K, V> inEntry = new Entry<>(key, value);
        extension();
        int hash = hash(key);
        int index = hashToIndex(hash);
        Entry<K, V> e = container[index];
        if (e == null) {
            container[index] = inEntry;
            length++;
            modCount++;
            rsl = true;
        } else if (e.key.equals(key)) {
            container[index] = inEntry;
            modCount++;
            rsl = true;
        }
            return rsl;
    }

    @SuppressWarnings("unchecked")
    private void extension() {
        if (length == load) {
            Iterator<Entry<K, V>> iterator = iterator();
            Entry<K, V>[] temp = new Entry[capacity * 2];
            while (iterator.hasNext()) {
                Entry<K, V> next = iterator.next();
                temp[hashToIndex(hash(next.key))] = next;
            }
            capacity *= 2;
            load = (int) (capacity * 0.75);
            container = temp;
        }
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = container[hashToIndex(hash(key))];
        if (entry != null) {
            return entry.value;
        }
        return null;
    }

    @Override
    public boolean delete(K key) {
        if (length == 0) {
            return false;
        }
        int hashKeyInput = hash(key);
        int index = hashToIndex(hashKeyInput);
        Entry<K, V> entry = container[index];
        if (entry == null) {
            return false;
        }
        if (hashKeyInput == hash(entry.key) && key.equals(entry.key)) {
            container[index] = null;
            length--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            int cursor = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                for (int i = cursor; i < capacity; i++) {
                    if (container[i] != null) {
                        cursor = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[cursor++];
            }
        };
    }

    private int hashToIndex(int hash) {
       return hash & (container.length - 1);
    }

    private int hash(K key) {
        return key == null ? 0 : (key.hashCode()) ^ (key.hashCode() >>> 16);
    }

    public int getCapacity() {
        return capacity;
    }

    static class Entry<K, V> {
        final K key;
        V value;

        Entry(K k, V v) {
            key = k;
            value = v;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
