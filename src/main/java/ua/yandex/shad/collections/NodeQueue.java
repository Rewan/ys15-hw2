package ua.yandex.shad.collections;

import java.util.InputMismatchException;

public class NodeQueue {

    private Node[] container;
    private int size;

    public NodeQueue() {
        container = new Node[1];
        this.size = 0;
    }

    public NodeQueue(int size) {
        container = new Node[size];
        this.size = 0;
    }

    public NodeQueue(Node[] arr) {
        container = arr.clone();
        this.size = arr.length;

        if (container.length == 0) {
            container = new Node[1];
        }
    }

    public int addNodes(Node... arr) {
        if (arr == null) {
            throw new InputMismatchException();
        }

        if (arr.length + size > container.length) {
            int n = container.length;

            while (n < arr.length + size) {
                n *= 2;
            }

            Node[] old = new Node[container.length];
            System.arraycopy(container, 0, old, 0, container.length);

            container = new Node[n];
            System.arraycopy(old, 0, container, 0, old.length);
        }

        if (arr.length > 0) {
            System.arraycopy(arr, 0, container, size, arr.length);

            size += arr.length;
        }

        return size;
    }

    public int size() {
        return size;
    }

    public int length() {
        return size;
    }

    public Node getNode(int x) {
        if (x < size) {
            return this.container[x];
        }
        throw new NullPointerException();
    }

    public Node setNode(int x, Node node) {
        if (x < size) {
            this.container[x] = node;
        }
        throw new NullPointerException();
    }

    public Node front() {
        if (size >= 1) {
            return container[0];
        }
        throw new NullPointerException("Container is empty.");
    }

    public void push(Node x) {
        addNodes(x);
    }

    public void pop() {
        if (size > 0) {
            System.arraycopy(container, 1, container, 0, size - 1);
            size--;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
