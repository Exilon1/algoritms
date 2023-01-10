package deque;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DequeTest {

    private final Deque<Integer> deque = new Deque<>();

    @AfterEach
    void clean() {
        deque.clear();
    }

    @Test
    void removeFrontTest() {
        fillSingleElement();

        assertEquals(1, deque.removeFront());
        assertEquals(0, deque.size());
        assertNull(deque.removeFront());

        clean();

        fillDifferentElements();

        assertEquals(1, deque.removeFront());
        assertEquals(4, deque.size());
        assertEquals(2, deque.removeFront());
        assertEquals(3, deque.size());
    }

    @Test
    void removeTailTest() {
        fillSingleElement();

        assertEquals(1, deque.removeTail());
        assertEquals(0, deque.size());
        assertNull(deque.removeTail());

        clean();

        fillDifferentElements();

        assertEquals(5, deque.removeTail());
        assertEquals(4, deque.size());
        assertEquals(4, deque.removeTail());
        assertEquals(3, deque.size());
    }

    @Test
    void addFrontTest() {
        deque.addFront(1);

        assertEquals(1, deque.size());
        assertEquals(1, deque.removeFront());

        deque.addFront(1);
        deque.addFront(2);

        assertEquals(2, deque.size());
        assertEquals(2, deque.removeFront());
        assertEquals(1, deque.removeFront());
    }

    @Test
    void addTailTest() {
        deque.addTail(1);

        assertEquals(1, deque.size());
        assertEquals(1, deque.removeTail());

        deque.addTail(1);
        deque.addTail(2);

        assertEquals(2, deque.size());
        assertEquals(2, deque.removeTail());
        assertEquals(1, deque.removeTail());
    }

    void fillSingleElement() {
        deque.addTail(1);
    }

    void fillDifferentElements() {
        deque.addTail(1);
        deque.addTail(2);
        deque.addTail(3);
        deque.addTail(4);
        deque.addTail(5);
    }
}
