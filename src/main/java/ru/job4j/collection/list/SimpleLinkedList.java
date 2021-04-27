package ru.job4j.collection.list;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        Node<E> newNode;
        if (size == 0) {
            newNode = new Node<>(null, value, null);
            first = newNode;
        } else {
            newNode = new Node<>(last, value, null);
            last.next = newNode;
        }
        last = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
       Node<E> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int cursor = 0;
            Node<E> currentNode = first;
            Node<E> returnNode;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                cursor++;
                returnNode = currentNode;
                currentNode = currentNode.next;
                return returnNode.element;
            }
        };
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
