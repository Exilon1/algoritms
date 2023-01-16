package nativedictionary;

import java.lang.reflect.Array;

class NativeDictionary<T> {
    public int size;
    public String[] slots;
    public T[] values;

    private int step;

    public NativeDictionary(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, size);
        step = 3;
    }

    public int hashFun(String key) {
        return Math.abs(key.hashCode()) % size;
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
    }

    public T get(String key) {
        int index = find(key);

        if (index == -1) {
            return null;
        }

        return values[index];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            slots[i] = null;
            values[i] = null;
        }
    }

    public int seekSlot(String value) {
        int index = find(value);

        if (index > -1) {
            return index;
        }

        index = hashFun(value);
        int count = 0;

        try {
            while (slots[index] != null && count < size) {
                count++;

                if (size - index - 1 < step) {
                    index = step - size + index;

                    if (size % step == 0) {
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

    private int find(String key) {
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
