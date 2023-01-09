package queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Queue<T> {

    private final LinkedList<T> linkedList;

    public Queue() {
        linkedList = new LinkedList<>();
    }

    public void enqueue(T item) {
        linkedList.addLast(item);
    }

    public T dequeue() {
        try {
            return linkedList.pop();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public int size() {
        return linkedList.size();
    }

}
