package queue;

import stack.InvertedStack;
import stack.Stack;

public class DoubleStackQueue<T> {

    private final Stack<T> forwardStack = new Stack<>();
    private final InvertedStack<T> reversedStack = new InvertedStack<>();

    public void enqueue(T item) {
        forwardStack.push(item);
    }

    public T dequeue() {
        if (reversedStack.size() == 0) {
            fillReversedStack();
        }

        return reversedStack.pop();
    }

    public int size() {
        return forwardStack.size() + reversedStack.size();
    }

    private void fillReversedStack() {
        while (forwardStack.size() > 0) {
            reversedStack.push(forwardStack.pop());
        }
    }
}
