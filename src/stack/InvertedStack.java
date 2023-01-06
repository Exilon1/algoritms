package stack;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class InvertedStack<T> {

    private final LinkedList<T> linkedList;

    public InvertedStack() {
        linkedList = new LinkedList<>();
    }

    public InvertedStack(List<T> list) {
        linkedList = new LinkedList<>(list);
    }

    public int size() {
        return linkedList.size();
    }

    public T pop() {
        try {
            return linkedList.pop();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void push(T val) {
        linkedList.push(val);
    }

    public T peek() {
        return linkedList.peek();
    }

    public void clear() {
        linkedList.clear();
    }
}
