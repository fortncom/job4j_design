package ru.job4j.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> optionalNode = findBy(parent);
        if (optionalNode.isPresent()) {
            Node<E> nodeParent = optionalNode.get();
            List<Node<E>> children = nodeParent.children;
            if (findBy(child).isEmpty()) {
                children.add(new Node<>(child));
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Optional<Node<E>> byPredicate =
                findByPredicate(el -> el.value.equals(value));
        if (byPredicate.isPresent()) {
            rsl = byPredicate;
        }
        return rsl;
    }

    @Override
    public boolean isBinary() {
        boolean rsl = false;
        Optional<Node<E>> byPredicate =
                findByPredicate(el -> el.children.size() > 2);
        if (byPredicate.isEmpty()) {
            rsl = true;
        }
        return rsl;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}