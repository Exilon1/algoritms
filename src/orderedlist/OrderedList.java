package orderedlist;

import java.util.ArrayList;

class Node<T> {
    public T value;
    public Node<T> next, prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T> {
    public Node<T> head, tail;
    private boolean _ascending;

    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        _ascending = asc;
    }

    public int compare(T v1, T v2) {
        if (v1 instanceof Comparable) {
            return ((Comparable<T>) v1).compareTo(v2);
        }

        throw new UnsupportedOperationException();
    }

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value);
            head.next = null;
            head.prev = null;
            tail = head;

            return;
        }

        int i = compare(head.value, value);
        if (i == 0 || (_ascending && i > 0) || (!_ascending && i < 0)) {
            head.prev = new Node<>(value);
            head.prev.next = head;
            head = head.prev;
            return;
        }

        int j = compare(tail.value, value);
        if (j == 0 || (_ascending && j < 0) || (!_ascending && j > 0)) {
            tail.next = new Node<>(value);
            tail.next.prev = tail;
            tail = tail.next;
            return;
        }

        Node<T> leftNode = head;
        Node<T> rightNode = tail;

        do {
            int k = compare(leftNode.value, value);
            if ((_ascending && k < 0 && compare(leftNode.next.value, value) >= 0) ||
                    (!_ascending && k > 0 && compare(leftNode.next.value, value) <= 0)) {
                Node<T> newNode = new Node<>(value);
                newNode.next = leftNode.next;
                newNode.prev = leftNode;
                leftNode.next.prev = newNode;
                leftNode.next = newNode;

                return;
            }

            int l = compare(rightNode.value, value);
            if ((_ascending && l > 0 && compare(rightNode.prev.value, value) <= 0) ||
                    (!_ascending && l < 0 && compare(rightNode.prev.value, value) >= 0)) {
                Node<T> newNode = new Node<>(value);
                newNode.next = rightNode;
                newNode.prev = rightNode.prev;
                rightNode.prev.next = newNode;
                rightNode.prev = newNode;

                return;
            }

            leftNode = leftNode.next;
            rightNode = rightNode.prev;

        } while (leftNode.prev != rightNode.next && leftNode.prev != rightNode);
    }

    public Node<T> find(T val) {
        if (head == null) {
            return null;
        }

        if (head == tail) {
            if (compare(head.value, val) == 0) {
                return head;
            }

            return null;
        }

        Node<T> leftNode = head;
        Node<T> rightNode = tail;

        do {
            int k = compare(leftNode.value, val);
            if (k == 0) {
                return leftNode;
            } else if ((_ascending && k > 0) || (!_ascending && k < 0)) {
                break;
            }

            int l = compare(rightNode.value, val);
            if (l == 0) {
                return rightNode;
            } else if ((_ascending && l < 0) || (!_ascending && l > 0)) {
                break;
            }

            leftNode = leftNode.next;
            rightNode = rightNode.prev;

        } while (leftNode.prev != rightNode.next && leftNode.prev != rightNode);

        return null;
    }

    public void delete(T val) {
        Node<T> foundNode = find(val);

        if (foundNode != null) {
            removeNode(foundNode);
        }
    }

    private void removeNode(Node<T> node) {
        if (node.prev == null && node.next == null) {
            head = null;
            tail = null;
        } else if (node.prev == null) {
            head = node.next;
            head.prev = null;
        } else if (node.next == null) {
            tail = node.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.prev = null;
        node.next = null;
    }

    public void clear(boolean asc) {
        _ascending = asc;
        head = null;
        tail = null;
    }

    public int count() {
        int n = 0;
        Node<T> node = head;
        while (node != null) {
            n++;
            node = node.next;
        }
        return n;
    }

    ArrayList<Node<T>> getAll() {
        ArrayList<Node<T>> r = new ArrayList<>();
        Node<T> node = head;

        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}
