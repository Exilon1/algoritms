package stack;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stack<T> {

    private final LinkedList<T> linkedList;

    public Stack() {
        linkedList = new LinkedList<>();
    }

    public int size() {
        return linkedList.size();
    }

    public T pop() {
        try {
            return linkedList.removeLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void push(T val) {
        linkedList.addLast(val);
    }

    public T peek() {
        try {
            return linkedList.getLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void clear() {
        linkedList.clear();
    }
}
