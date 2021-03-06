package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    private int size = 0;

    public void add(T value) {
       Node<T> newNode = new Node<>(value, null);
       if (head == null) {
           head = newNode;
           size++;
           return;
       }
       Node<T> tail = head;
       while (tail.next != null) {
           tail = tail.next;
       }
       tail.next = newNode;
        size++;
    }

    public T deleteFirst() {
        Node<T> node = head;
        if (node == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
        size--;
        return node.value;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> currentNode = head;
        Node<T> nextNode = null;
        while (currentNode != null) {
            Node<T> previous = currentNode.next;
            currentNode.next = nextNode;
            nextNode = currentNode;
            currentNode = previous;
        }
        head = nextNode;
        return true;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
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
