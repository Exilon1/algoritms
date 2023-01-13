package hashtable;

import java.util.Random;

public class HashTable {
    public int size;
    public int step;
    public String[] slots;

    public HashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];

        for (int i = 0; i < size; i++) {
            slots[i] = null;
        }
    }

    public int hashFun(String value) {
        return Math.abs(value.hashCode()) % size;
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

    public int put(String value) {
        int index = seekSlot(value);

        if (index > -1) {
            slots[index] = value;
        }

        return index;
    }

    public int find(String value) {
        int index = hashFun(value);

        try {
            if (slots[index] == null) {
                return -1;
            }

            if (slots[index].equals(value)) {
                return index;
            }

            for (int i = 0; i < size; i++) {
                if (i == index) {
                    continue;
                }

                if (value.equals(slots[i])) {
                    return i;
                }
            }

            return -1;
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            slots[i] = null;
        }
    }

    public int absolutHashFun(int val) {
        Random random = new Random();

        int a = random.ints(0, size - 1).findFirst().orElse(size - 1);
        int b = random.ints(0, size - 1).findFirst().orElse(size - 1);
        int p = 31;

        return (a * val + b) % p % size;
    }
}
