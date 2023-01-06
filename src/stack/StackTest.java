package stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class StackTest {

    private final Stack<Integer> stack = new Stack<>();

    @AfterEach
    void clean() {
        stack.clear();
    }

    @Test
    void sizeTest() {
        assertEquals(0, stack.size());

        fillSingleElement();

        assertEquals(1, stack.size());

        clean();

        fillDifferentElements();

        assertEquals(5, stack.size());
    }

    @Test
    void popTest() {
        assertNull(stack.pop());

        fillSingleElement();

        assertEquals(1, stack.pop());
        assertEquals(0, stack.size());
        assertNull(stack.pop());

        clean();

        fillDifferentElements();

        assertEquals(5, stack.pop());
        assertEquals(4, stack.size());
        assertEquals(4, stack.pop());
        assertEquals(3, stack.size());
    }

    @Test
    void pushTest() {
        assertNull(stack.pop());

        stack.push(1);

        assertEquals(1, stack.size());
        assertEquals(1, stack.peek());

        stack.push(2);

        assertEquals(2, stack.size());
        assertEquals(2, stack.peek());
    }

    @Test
    void peekTest() {
        assertNull(stack.peek());

        fillSingleElement();

        assertEquals(1, stack.peek());

        clean();

        fillDifferentElements();

        assertEquals(5, stack.peek());
    }

    void fillSingleElement() {
        stack.push(1);
    }

    void fillDifferentElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
    }
}
