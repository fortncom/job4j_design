package ru.job4j.collection;


public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    public int size() {
        return linked.size();
    }

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

}