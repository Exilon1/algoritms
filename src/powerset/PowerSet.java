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

        for (String slot : slots) {
            if (slot != null && set2.get(slot)) {
                powerSet.put(slot);
            }
        }

        return powerSet;
    }

    public PowerSet union(PowerSet set2) {
        PowerSet powerSet = new PowerSet();
        String[] slots2 = set2.getSlots();

        for (String s : slots2) {
            if (s != null) {
                powerSet.put(s);
            }
        }

        for (String slot : slots) {
            if (slot != null) {
                powerSet.put(slot);
            }
        }

        return powerSet;
    }

    public PowerSet difference(PowerSet set2) {
        PowerSet powerSet = new PowerSet();

        for (String slot : slots) {
            if (slot != null && !set2.get(slot)) {
                powerSet.put(slot);
            }
        }

        return powerSet;
    }

    public boolean isSubset(PowerSet set2) {
        String[] slots2 = set2.getSlots();

        if (set2.size() > size) {
            return false;
        }

        for (String s : slots2) {
            if (s != null && !get(s)) {
                return false;
            }
        }

        return true;
    }

    public void clear() {
        size = 0;
        Arrays.fill(slots, null);
    }

    public String[] getSlots() {
        return slots;
    }

    private int hashFun(String value) {
        int hash = value.hashCode();
        int y = hash >> 31;

        return ((hash ^ y) - y) % slots.length;
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
