package orderedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderedListTest {

    private final OrderedList<Integer> integerList = new OrderedList<>(true);

    @AfterEach
    void clean() {
        clean(true);
    }

    void clean(boolean val) {
        integerList.clear(val);
    }

    @Test
    void addTest() {
        integerList.add(5);
        assertEquals(5, integerList.getAll().get(0).value);
        assertEquals(1, integerList.count());


        clean(false);

        integerList.add(5);
        assertEquals(5, integerList.getAll().get(0).value);
        assertEquals(1, integerList.count());

        clean(true);

        ArrayList<Node<Integer>> list;

        fillSingleElement();

        integerList.add(5);
        list = integerList.getAll();
        assertEquals(5, list.get(0).value);
        assertEquals(5, list.get(1).value);
        assertEquals(2, integerList.count());

        clean(false);

        fillSingleElement();

        integerList.add(5);
        list = integerList.getAll();
        assertEquals(5, list.get(0).value);
        assertEquals(5, list.get(1).value);
        assertEquals(2, integerList.count());

        clean(true);

        fillSingleElement();

        integerList.add(4);
        list = integerList.getAll();
        assertEquals(4, list.get(0).value);
        assertEquals(5, list.get(1).value);
        assertEquals(2, integerList.count());

        clean(false);

        fillSingleElement();

        integerList.add(4);
        list = integerList.getAll();
        assertEquals(5, list.get(0).value);
        assertEquals(4, list.get(1).value);
        assertEquals(2, integerList.count());

        clean(true);

        fillDoubleElement();

        integerList.add(2);
        list = integerList.getAll();
        assertEquals(2, list.get(0).value);
        assertEquals(3, list.get(1).value);
        assertEquals(5, list.get(2).value);
        assertEquals(3, integerList.count());

        clean(false);

        fillDoubleElement();

        integerList.add(2);
        list = integerList.getAll();
        assertEquals(5, list.get(0).value);
        assertEquals(3, list.get(1).value);
        assertEquals(2, list.get(2).value);
        assertEquals(3, integerList.count());

        clean(true);

        fillDoubleElement();

        integerList.add(3);
        list = integerList.getAll();
        assertEquals(3, list.get(0).value);
        assertEquals(3, list.get(1).value);
        assertEquals(5, list.get(2).value);
        assertEquals(3, integerList.count());

        clean(false);

        fillDoubleElement();

        integerList.add(3);
        list = integerList.getAll();
        assertEquals(5, list.get(0).value);
        assertEquals(3, list.get(1).value);
        assertEquals(3, list.get(2).value);
        assertEquals(3, integerList.count());

        clean(true);

        fillDoubleElement();

        integerList.add(4);
        list = integerList.getAll();
        assertEquals(3, list.get(0).value);
        assertEquals(4, list.get(1).value);
        assertEquals(5, list.get(2).value);
        assertEquals(3, integerList.count());

        clean(false);

        fillDoubleElement();

        integerList.add(4);
        list = integerList.getAll();
        assertEquals(5, list.get(0).value);
        assertEquals(4, list.get(1).value);
        assertEquals(3, list.get(2).value);
        assertEquals(3, integerList.count());

        clean(true);

        fillDoubleElement();

        integerList.add(6);
        list = integerList.getAll();
        assertEquals(3, list.get(0).value);
        assertEquals(5, list.get(1).value);
        assertEquals(6, list.get(2).value);
        assertEquals(3, integerList.count());

        clean(false);

        fillDoubleElement();

        integerList.add(6);
        list = integerList.getAll();
        assertEquals(6, list.get(0).value);
        assertEquals(5, list.get(1).value);
        assertEquals(3, list.get(2).value);
        assertEquals(3, integerList.count());

        clean(true);

        fillAscendingSequence();

        AtomicInteger i = new AtomicInteger();
        integerList.getAll().forEach(n -> assertEquals(i.incrementAndGet(), n.value));

        clean(false);

        fillAscendingSequence();

        i.set(6);
        integerList.getAll().forEach(n -> assertEquals(i.decrementAndGet(), n.value));

        clean(true);

        fillDescendingSequence();

        i.set(0);
        integerList.getAll().forEach(n -> assertEquals(i.incrementAndGet(), n.value));

        clean(false);

        fillDescendingSequence();

        i.set(6);
        integerList.getAll().forEach(n -> assertEquals(i.decrementAndGet(), n.value));

        clean(true);

        fillRandomSequence();

        i.set(0);
        integerList.getAll().forEach(n -> assertEquals(i.incrementAndGet(), n.value));

        clean(false);

        fillRandomSequence();

        i.set(6);
        integerList.getAll().forEach(n -> assertEquals(i.decrementAndGet(), n.value));
    }

    @Test
    void findTest() {
        assertNull(integerList.find(1));

        fillSingleElement();

        assertEquals(5, integerList.find(5).value);

        clean(true);

        fillDoubleElement();

        assertEquals(3, integerList.find(3).value);
        assertEquals(5, integerList.find(5).value);

        clean(false);

        fillDoubleElement();

        assertEquals(3, integerList.find(3).value);
        assertEquals(5, integerList.find(5).value);

        clean(true);

        fillRandomSequence();

        assertNull(integerList.find(0 ));
        assertEquals(1, integerList.find(1).value);
        assertEquals(5, integerList.find(5).value);
        assertEquals(3, integerList.find(3).value);

        clean(false);

        fillRandomSequence();

        assertNull(integerList.find(0 ));
        assertEquals(1, integerList.find(1).value);
        assertEquals(5, integerList.find(5).value);
        assertEquals(3, integerList.find(3).value);
    }

    @Test
    void deleteTest() {
        integerList.delete(0);
        assertEquals(0, integerList.count());

        fillSingleElement();

        integerList.delete(0);
        assertEquals(1, integerList.count());
        integerList.delete(5);
        assertNull(integerList.find(5));
        assertEquals(0, integerList.count());

        clean(true);

        fillRandomSequence();

        integerList.delete(5);
        assertNull(integerList.find(5));
        assertEquals(4, integerList.count());

        integerList.delete(1);
        assertNull(integerList.find(1));
        assertEquals(3, integerList.count());

        integerList.delete(3);
        assertNull(integerList.find(3));
        assertEquals(2, integerList.count());

        clean(false);

        fillRandomSequence();

        integerList.delete(5);
        assertNull(integerList.find(5));
        assertEquals(4, integerList.count());

        integerList.delete(1);
        assertNull(integerList.find(1));
        assertEquals(3, integerList.count());

        integerList.delete(3);
        assertNull(integerList.find(3));
        assertEquals(2, integerList.count());
    }

    void fillSingleElement() {
        integerList.add(5);
    }

    void fillDoubleElement() {
        integerList.add(3);
        integerList.add(5);
    }

    void fillAscendingSequence() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
    }

    void fillDescendingSequence() {
        integerList.add(5);
        integerList.add(4);
        integerList.add(3);
        integerList.add(2);
        integerList.add(1);
    }

    void fillRandomSequence() {
        integerList.add(4);
        integerList.add(3);
        integerList.add(5);
        integerList.add(1);
        integerList.add(2);
    }
}
