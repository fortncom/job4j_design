package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;

    public void add(T value) {
       Node<T> newNode = new Node<>(value, null);
       if (head == null) {
           head = newNode;
           return;
       }
       Node<T> tail = head;
       while (tail.next != null) {
           tail = tail.next;
       }
       tail.next = newNode;
    }

    public T deleteFirst() {
        Node<T> node = head;
        if (node == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
        return node.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

}
