package bloomfilter;

import java.math.BigInteger;
import java.util.Random;

public class BloomFilter {
    public int filter_len;
    private int filter;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        filter = 0;
    }

    public int hash1(String str1) {
        var random = new Random(17);

        return getHash(str1, random);
    }

    public int hash2(String str1) {
        var random = new Random(223);

        return getHash(str1, random);
    }

    private int getHash(String string, Random random) {
        int iter = 0;

        for (int i = 0; i < string.length(); i++) {
            int code = string.charAt(i);
            iter = (iter + code) * (random.nextInt(31) + 1);
        }

        if (iter == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE % filter_len;
        }

        return Math.abs(iter) % filter_len;
    }

    public void add(String str1) {
        filter = filter | new BigInteger(String.valueOf(2)).pow(hash1(str1)).intValue();
        filter = filter | new BigInteger(String.valueOf(2)).pow(hash2(str1)).intValue();
    }

    public boolean isValue(String str1) {
        int hashOne = new BigInteger(String.valueOf(2)).pow(hash1(str1)).intValue();
        int hashTwo = new BigInteger(String.valueOf(2)).pow(hash1(str1)).intValue();

        return (filter & hashOne) == hashOne && (filter & hashTwo) == hashTwo;
    }

    public void clear() {
        filter = 0;
    }
}
