package nativecache;

import java.lang.reflect.Array;

public class NativeCache<T> {

    public int size;
    public String[] slots;
    public T[] values;
    public int[] hits;

    private int step;

    public NativeCache(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, size);
        hits = new int[size];
        step = 3;
    }

    public int hashFun(String key) {
        int hash = key.hashCode();

        if (hash == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE % size;
        }

        return Math.abs(hash) % size;
    }

    public boolean isKey(String key) {
        int index = find(key);

        if (index == -1) {
            return false;
        }

        return true;
    }

    public void put(String key, T value) {
        int index = seekSlot(key);

        if (index > -1) {
            slots[index] = key;
            values[index] = value;
        }

        if (index == -1) {
            int hitIndex = 0;
            int hit = hits[0];

            for (int i = 1; i < size; i++) {
                if (hit > hits[i]) {
                    hit = hits[i];
                    hitIndex = i;
                }
            }

            slots[hitIndex] = key;
            values[hitIndex] = value;
            hits[hitIndex] = 0;
        }
    }

    public T get(String key) {
        int index = find(key);

        if (index == -1) {
            return null;
        }
        hits[index] = hits[index] + 1;

        return values[index];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            slots[i] = null;
            values[i] = null;
        }
    }

    private int seekSlot(String value) {
        int index = find(value);

        if (index > -1) {
            return index;
        }

        index = hashFun(value);

        try {
            for (int i = 0; i < slots.length && slots[index] != null; i++) {
                if (slots.length - index - 1 < step) {
                    index = step - slots.length + index;

                    if (slots.length % step == 0) {
                        index++;
                    }
                    continue;
                }

                index = index + step;
            }

            return slots[index] == null ? index : -1;
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public int find(String key) {
        int index = hashFun(key);

        try {
            if (slots[index] == null) {
                return -1;
            }

            if (slots[index].equals(key)) {
                return index;
            }

            for (int i = 0; i < size; i++) {
                if (i == index) {
                    continue;
                }

                if (key.equals(slots[i])) {
                    return i;
                }
            }

            return -1;
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }
}
