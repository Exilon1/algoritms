package dynarray;

import java.lang.reflect.Array;

public class DynArray<T> {
    public T[] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz) {
        clazz = clz;

        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity) {
        T[] newArray = (T[]) Array.newInstance(clazz, new_capacity);

        if (array != null) {
            System.arraycopy(array, 0, newArray, 0, count); // O(count)
        }

        array = newArray;
        capacity = new_capacity;
    }

    public T getItem(int index) {
        verifyIndex(index);

        return array[index];
    }

    public void append(T itm) {
        if (count == capacity) {
            expandArray();
        }
        array[count] = itm;
        count++;
    }

    // O(count)
    public void insert(T itm, int index) {
        if (index == count) {
            append(itm);
            return;
        }

        verifyIndex(index);

        if (count == capacity) {
            expandArray();
        }

        for (int i = count; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = itm;
        count++;
    }

    // O(count)
    public void remove(int index) {
        verifyIndex(index);

        for (int i = index; i < count; i++) {
            array[i] = array[i + 1];
        }

        count--;

        if (checkConditionToReduce()) {
            reduceArray();
        }
    }

    public void clear() {
        array = null;
        count = 0;
        makeArray(16);
    }

    private void verifyIndex(int i) throws IndexOutOfBoundsException {
        if (i >= count || i < 0) {
            throw new IndexOutOfBoundsException(i);
        }
    }

    private void expandArray() {
        makeArray(capacity * 2);
    }

    private void reduceArray() {
        int newCapacity = (int) (capacity / 1.5);
        if (newCapacity < 16) {
            makeArray(16);
        } else {
            makeArray(newCapacity);
        }
    }

    private boolean checkConditionToReduce() {
        return (capacity % 2 == 0 && count < capacity / 2) ||
                (capacity % 2 != 0 && count <= capacity / 2);
    }
}
