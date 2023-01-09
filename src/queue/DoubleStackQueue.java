package queue;

import stack.InvertedStack;
import stack.Stack;

public class DoubleStackQueue<T> {

    private final Stack<T> forwardStack = new Stack<>();
    private final InvertedStack<T> reverseStack = new InvertedStack<>();

    private boolean forwardIsActive = true;

    public void enqueue(T item) {
        moveToForward();

        forwardStack.push(item);
    }

    public T dequeue() {
        moveToReverse();
        return reverseStack.pop();
    }

    public int size() {
        if (forwardIsActive) {
            return forwardStack.size();
        }

        return reverseStack.size();
    }

    private void moveToForward() {
        while (reverseStack.size() > 0) {
            forwardStack.push(reverseStack.pop());
        }
        forwardIsActive = true;
    }

    private void moveToReverse() {
        while (forwardStack.size() > 0) {
            reverseStack.push(forwardStack.pop());
        }
        forwardIsActive = false;
    }
}
