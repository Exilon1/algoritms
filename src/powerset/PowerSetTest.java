package powerset;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PowerSetTest {

    private final PowerSet powerSet = new PowerSet();
    private final PowerSet testPowerSet = new PowerSet();

    @AfterEach
    void clean() {
        powerSet.clear();
        testPowerSet.clear();
    }

    @Test
    void putTest() {
        powerSet.put("a");
        assertTrue(powerSet.get("a"));
        assertEquals(1, powerSet.size());

        powerSet.put("a");
        assertTrue(powerSet.get("a"));
        assertEquals(1, powerSet.size());
    }

    @Test
    void removeTest() {
        assertFalse(powerSet.remove("a"));

        fillSet();

        assertFalse(powerSet.remove("bb"));
        assertTrue(powerSet.remove("aaa"));
        assertEquals(16, powerSet.size());
        assertFalse(powerSet.get("aaa"));

        clean();

        for (int i = 0; i < 10000; i++) {
            powerSet.put(String.valueOf(i));
        }

        for (int i = 0; i < 10000; i++) {
            assertTrue(powerSet.remove(String.valueOf(i)));
            assertFalse(powerSet.get(String.valueOf(i)));
            assertEquals(9999 - i, powerSet.size());
        }
    }

    @Test
    void intersectionTest() {
        PowerSet set = powerSet.intersection(new PowerSet());
        assertEquals(0, set.size());

        fillSet();

        set = powerSet.intersection(powerSet);
        assertEquals(17, set.size());

        set = powerSet.intersection(new PowerSet());
        assertEquals(0, set.size());

        set = new PowerSet().intersection(powerSet);
        assertEquals(0, set.size());

        fillTestSet();

        set = powerSet.intersection(testPowerSet);
        assertEquals(4, set.size());
        assertTrue(set.get("a"));
        assertTrue(set.get("aa"));
        assertTrue(set.get("aaa"));
        assertTrue(set.get("aaaa"));

        set = testPowerSet.intersection(powerSet);
        assertEquals(4, set.size());
        assertTrue(set.get("a"));
        assertTrue(set.get("aa"));
        assertTrue(set.get("aaa"));
        assertTrue(set.get("aaaa"));
    }

    @Test
    void unionTest() {
        PowerSet set = powerSet.union(new PowerSet());
        assertEquals(0, set.size());

        fillSet();

        set = powerSet.union(powerSet);
        assertEquals(17, set.size());

        set = powerSet.union(new PowerSet());
        assertEquals(17, set.size());

        set = new PowerSet().union(powerSet);
        assertEquals(17, set.size());

        fillTestSet();

        set = powerSet.union(testPowerSet);
        assertEquals(19, set.size());
        assertTrue(set.get("a"));
        assertTrue(set.get("aa"));
        assertTrue(set.get("bb"));
        assertTrue(set.get("bbb"));
        assertTrue(set.get("aaaaaaaaaaaaaaaa"));
        assertTrue(set.get("aaaaaaaaaaaaaaaaa"));

        set = testPowerSet.union(powerSet);
        assertTrue(set.get("a"));
        assertTrue(set.get("aa"));
        assertTrue(set.get("bb"));
        assertTrue(set.get("bbb"));
        assertTrue(set.get("aaaaaaaaaaaaaaaa"));
        assertTrue(set.get("aaaaaaaaaaaaaaaaa"));
    }

    @Test
    void differenceTest() {
        PowerSet set = powerSet.difference(new PowerSet());
        assertEquals(0, set.size());

        fillSet();

        set = powerSet.difference(powerSet);
        assertEquals(0, set.size());

        set = powerSet.difference(new PowerSet());
        assertEquals(17, set.size());

        set = new PowerSet().difference(powerSet);
        assertEquals(0, set.size());

        fillTestSet();

        set = powerSet.difference(testPowerSet);
        assertEquals(13, set.size());

        set = testPowerSet.difference(powerSet);
        assertEquals(2, set.size());
        assertTrue(set.get("bb"));
        assertTrue(set.get("bbb"));
    }

    @Test
    void isSubsetTest() {
        assertTrue(powerSet.isSubset(new PowerSet()));

        fillSet();
        fillTestSubset();

        assertTrue(powerSet.isSubset(testPowerSet));
        assertFalse(testPowerSet.isSubset(powerSet));

        clean();

        fillSet();
        fillTestSet();

        assertFalse(powerSet.isSubset(testPowerSet));
        assertFalse(testPowerSet.isSubset(powerSet));
    }

    @Nested
    class NestPerformanceTest {

        @BeforeEach
        public void init() {
            for (int i = 1; i <= 19000; i++) {
                powerSet.put(String.valueOf(i));
                testPowerSet.put(String.valueOf(i + 1000));
            }
        }

        @AfterEach
        void clean() {
            powerSet.clear();
            testPowerSet.clear();
        }

        @Test
        @Timeout(value = 2)
        public void intersectionTest() {
            powerSet.intersection(testPowerSet);
        }

        @Test
        @Timeout(value = 2)
        public void unionTest() {
            powerSet.union(testPowerSet);
        }

        @Test
        @Timeout(value = 2)
        public void differenceTest() {
            powerSet.difference(testPowerSet);
        }

        @Test
        @Timeout(value = 2)
        public void isSubsetTest() {
            powerSet.isSubset(testPowerSet);
        }

    }

    private void fillSet() {
        powerSet.put("a");
        powerSet.put("aa");
        powerSet.put("aaa");
        powerSet.put("aaaa");
        powerSet.put("aaaaa");
        powerSet.put("aaaaaa");
        powerSet.put("aaaaaaa");
        powerSet.put("aaaaaaaa");
        powerSet.put("aaaaaaaaa");
        powerSet.put("aaaaaaaaaa");
        powerSet.put("aaaaaaaaaaa");
        powerSet.put("aaaaaaaaaaaa");
        powerSet.put("aaaaaaaaaaaaa");
        powerSet.put("aaaaaaaaaaaaaa");
        powerSet.put("aaaaaaaaaaaaaaa");
        powerSet.put("aaaaaaaaaaaaaaaa");
        powerSet.put("aaaaaaaaaaaaaaaaa");
    }

    private void fillTestSet() {
        testPowerSet.put("a");
        testPowerSet.put("aa");
        testPowerSet.put("aaa");
        testPowerSet.put("aaaa");
        testPowerSet.put("bb");
        testPowerSet.put("bbb");
    }

    private void fillTestSubset() {
        testPowerSet.put("a");
        testPowerSet.put("aa");
        testPowerSet.put("aaa");
        testPowerSet.put("aaaa");
        testPowerSet.put("aaaaaaaaaaaaaaaaa");
    }
}
