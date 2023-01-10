package deque;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Deque<T> {

    private final LinkedList<T> linkedList;

    public Deque() {
        linkedList = new LinkedList<>();
    }

    public Deque(List<T> list) {
        this.linkedList = new LinkedList<>(list);
    }

    public void addFront(T item) {
        linkedList.addFirst(item);
    }

    public void addTail(T item) {
        linkedList.addLast(item);
    }

    public T removeFront() {
        try {
            return linkedList.removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public T removeTail() {
        try {
            return linkedList.removeLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public int size() {
        return linkedList.size();
    }

    public void clear() {
        linkedList.clear();
    }
}
