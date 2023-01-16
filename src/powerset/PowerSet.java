package powerset;

import java.util.Arrays;

public class PowerSet {

    private int size;
    private final int step;
    private final String[] slots;

    public PowerSet() {
        size = 0;
        step = 11;
        slots = new String[20000];

        Arrays.fill(slots, null);
    }

    public int size() {
        return size;
    }

    public void put(String value) {
        int index = seekSlot(value);

        if (index > -1 && slots[index] == null) {
            size++;
        }

        if (index > -1) {
            slots[index] = value;
        }
    }

    public boolean get(String value) {
        return find(value) > -1;
    }

    public boolean remove(String value) {
        int index = find(value);

        if (index > -1) {
            slots[index] = null;
            size--;

            return true;
        }
        return false;
    }

    public PowerSet intersection(PowerSet set2) {
        PowerSet powerSet = new PowerSet();

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && set2.get(slots[i])) {
                powerSet.put(slots[i]);
            }
        }

        return powerSet;
    }

    public PowerSet union(PowerSet set2) {
        PowerSet powerSet = new PowerSet();
        String[] slots2 = set2.getSlots();

        for (int i = 0; i < slots2.length; i++) {
            if (slots2[i] != null) {
                powerSet.put(slots2[i]);
            }
        }

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null) {
                powerSet.put(slots[i]);
            }
        }

        return powerSet;
    }

    public PowerSet difference(PowerSet set2) {
        PowerSet powerSet = new PowerSet();

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && !set2.get(slots[i])) {
                powerSet.put(slots[i]);
            }
        }

        return powerSet;
    }

    public boolean isSubset(PowerSet set2) {
        String[] slots2 = set2.getSlots();

        if (set2.size() > size) {
            return false;
        }

        for (int i = 0; i < slots2.length; i++) {
            if (slots2[i] != null && !get(slots2[i])) {
                return false;
            }
        }

        return true;
    }

    public void clear() {
        Arrays.fill(slots, null);
    }

    public String[] getSlots() {
        return slots;
    }

    private int hashFun(String value) {
        return Math.abs(value.hashCode()) % slots.length;
    }

    private int find(String value) {
        int index = hashFun(value);

        try {
            if (slots[index] == null) {
                return -1;
            }

            if (slots[index].equals(value)) {
                return index;
            }

            for (int i = 0; i < slots.length; i++) {
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
}
