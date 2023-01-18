package bloomfilter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BloomFilterTest {

    private final BloomFilter bloomFilter = new BloomFilter(32);

    @Test
    void bloomFilterTest() {
        assertFalse(bloomFilter.isValue("0123456789"));

        bloomFilter.add("0123456789");
        bloomFilter.add("1234567890");
        bloomFilter.add("2345678901");
        bloomFilter.add("3456789012");
        bloomFilter.add("4567890123");
        bloomFilter.add("5678901234");
        bloomFilter.add("6789012345");
        bloomFilter.add("7890123456");
        bloomFilter.add("8901234567");
        bloomFilter.add("9012345678");

        assertTrue(bloomFilter.isValue("0123456789"));
        assertTrue(bloomFilter.isValue("1234567890"));
        assertTrue(bloomFilter.isValue("2345678901"));
        assertTrue(bloomFilter.isValue("3456789012"));
        assertTrue(bloomFilter.isValue("4567890123"));
        assertTrue(bloomFilter.isValue("5678901234"));
        assertTrue(bloomFilter.isValue("6789012345"));
        assertTrue(bloomFilter.isValue("7890123456"));
        assertTrue(bloomFilter.isValue("8901234567"));
        assertTrue(bloomFilter.isValue("9012345678"));
    }
}
