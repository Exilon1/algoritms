package dynarray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class DynArrayTest {

    private DynArray<Integer> array = new DynArray<>(Integer.class);

    @AfterEach
    void clean() {
        array.clear();
    }

    @Test
    void insertWhenCapacityIsNotExceededTest() {
        assertEquals(16, array.capacity);
        assertEquals(0, array.count);

        array.insert(0,0);

        assertEquals(0, array.getItem(0));
        assertEquals(16, array.capacity);
        assertEquals(1, array.count);

        clean();

        fillLessThanCapacityFirstPreset();

        array.insert(0,0);

        assertEquals(0, array.getItem(0));
        assertEquals(1, array.getItem(1));
        assertEquals(16, array.capacity);
        assertEquals(10, array.count);

        array.insert(0,10);

        assertEquals(0, array.getItem(10));
        assertEquals(9, array.getItem(9));
        assertEquals(16, array.capacity);
        assertEquals(11, array.count);

        clean();

        fillLessThanCapacityFirstPreset();

        array.insert(0,4);
        array.insert(0,4);
        array.insert(0,4);

        assertEquals(4, array.getItem(3));
        assertEquals(0, array.getItem(4));
        assertEquals(0, array.getItem(5));
        assertEquals(0, array.getItem(6));
        assertEquals(5, array.getItem(7));
        assertEquals(16, array.capacity);
        assertEquals(12, array.count);

        clean();

        fillLessThanCapacitySecondPreset();

        array.insert(0,15);

        assertEquals(0, array.getItem(15));
        assertEquals(16, array.capacity);
        assertEquals(16, array.count);
    }

    @Test
    void insertWhenCapacityIsExceededTest() {
        fillUpToCapacity();

        assertEquals(16, array.capacity);
        assertEquals(16, array.count);

        array.insert(0,0);

        assertEquals(0, array.getItem(0));
        assertEquals(1, array.getItem(1));
        assertEquals(32, array.capacity);
        assertEquals(17, array.count);

        clean();

        fillUpToCapacity();

        assertEquals(16, array.capacity);
        assertEquals(16, array.count);

        array.insert(0,16);

        assertEquals(16, array.getItem(15));
        assertEquals(0, array.getItem(16));
        assertEquals(32, array.capacity);
        assertEquals(17, array.count);
    }

    @Test
    void insertByInvalidIndexTest() {
        fillLessThanCapacityFirstPreset();

        assertThrowsExactly(IndexOutOfBoundsException.class, () -> array.insert(0, -1));
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> array.insert(0, 12));
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> array.insert(0, 42));

        assertEquals(16, array.capacity);
        assertEquals(9, array.count);
    }

    @Test
    void removeWithoutCapacityChangedTest() {
        fillMoreThanCapacity();

        assertEquals(32, array.capacity);
        assertEquals(18, array.count);

        array.remove(0);

        assertEquals(2, array.getItem(0));
        assertEquals(32, array.capacity);
        assertEquals(17, array.count);

        array.remove(16);

        assertEquals(17, array.getItem(15));
        assertEquals(32, array.capacity);
        assertEquals(16, array.count);
    }

    @Test
    void removeWithCapacityChangedTest() {
        fillMoreThanCapacity();

        assertEquals(32, array.capacity);
        assertEquals(18, array.count);

        array.remove(8);
        array.remove(8);
        array.remove(8);

        assertEquals(12, array.getItem(8));
        assertEquals(21, array.capacity);
        assertEquals(15, array.count);

        array.remove(0);
        array.remove(0);
        array.remove(0);
        array.remove(0);

        assertEquals(21, array.capacity);
        assertEquals(11, array.count);

        array.remove(0);

        assertEquals(16, array.capacity);
        assertEquals(10, array.count);

        clean();

        fillLessThanCapacityFirstPreset();

        array.append(0);
        array.append(0);
        array.append(0);
        array.append(0);

        array.makeArray(25);

        assertEquals(25, array.capacity);
        assertEquals(13, array.count);

        array.remove(0);

        assertEquals(16, array.capacity);
        assertEquals(12, array.count);

        clean();

        fillLessThanCapacityFirstPreset();

        array.remove(0);
        array.remove(0);

        assertEquals(16, array.capacity);
        assertEquals(7, array.count);
    }

    @Test
    void removeByInvalidIndexTest() {
        fillLessThanCapacityFirstPreset();

        assertThrowsExactly(IndexOutOfBoundsException.class, () -> array.remove(-1));
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> array.remove(12));
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> array.remove(42));

        assertEquals(16, array.capacity);
        assertEquals(9, array.count);
    }

    void fillLessThanCapacityFirstPreset() {
        array.append(1);
        array.append(2);
        array.append(3);
        array.append(4);
        array.append(5);
        array.append(6);
        array.append(7);
        array.append(8);
        array.append(9);
    }

    void fillLessThanCapacitySecondPreset() {
        array.append(1);
        array.append(2);
        array.append(3);
        array.append(4);
        array.append(5);
        array.append(6);
        array.append(7);
        array.append(8);
        array.append(9);
        array.append(10);
        array.append(11);
        array.append(12);
        array.append(13);
        array.append(14);
        array.append(15);
    }

    void fillUpToCapacity() {
        array.append(1);
        array.append(2);
        array.append(3);
        array.append(4);
        array.append(5);
        array.append(6);
        array.append(7);
        array.append(8);
        array.append(9);
        array.append(10);
        array.append(11);
        array.append(12);
        array.append(13);
        array.append(14);
        array.append(15);
        array.append(16);
    }

    void fillMoreThanCapacity() {
        array.append(1);
        array.append(2);
        array.append(3);
        array.append(4);
        array.append(5);
        array.append(6);
        array.append(7);
        array.append(8);
        array.append(9);
        array.append(10);
        array.append(11);
        array.append(12);
        array.append(13);
        array.append(14);
        array.append(15);
        array.append(16);
        array.append(17);
        array.append(18);
    }
}
