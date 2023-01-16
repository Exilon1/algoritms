package nativedictionary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NativeDictionaryTest {

    private final NativeDictionary<Integer> nativeDictionary = new NativeDictionary<>(17, Integer.class);

    @AfterEach
    void clean() {
        nativeDictionary.clear();
    }

    @Test
    void putTest() {
        nativeDictionary.put("aaa", 3);
        assertEquals(3, nativeDictionary.get("aaa"));

        clean();

        fillDictionaryWithCollisions();

        nativeDictionary.put("aaa", 42);
        assertEquals(42, nativeDictionary.get("aaa"));

        nativeDictionary.put("bb", 144);
        assertEquals(144, nativeDictionary.get("bb"));

        nativeDictionary.put("bbb", 144);
        assertNull(nativeDictionary.get("bbb"));
    }

    @Test
    void getTest() {
        assertNull(nativeDictionary.get("bbb"));

        fillDictionaryWithCollisions();

        assertNull(nativeDictionary.get("bbb"));
        assertEquals(1, nativeDictionary.get("a"));
        assertEquals(3, nativeDictionary.get("aaa"));
        assertEquals(16, nativeDictionary.get("aaaaaaaaaaaaaaaa"));
    }

    @Test
    void isKeyTest() {
        assertFalse(nativeDictionary.isKey("bbb"));

        fillDictionaryWithCollisions();

        assertFalse(nativeDictionary.isKey("bbb"));
        assertTrue(nativeDictionary.isKey("a"));
        assertTrue(nativeDictionary.isKey("aaa"));
        assertTrue(nativeDictionary.isKey("aaaaaaaaaaaaaaaa"));
    }

    private void fillDictionaryWithCollisions() {
        nativeDictionary.put("a", 1);
        nativeDictionary.put("aa", 2);
        nativeDictionary.put("aaa", 3);
        nativeDictionary.put("aaaa", 4);
        nativeDictionary.put("aaaaa", 5);
        nativeDictionary.put("aaaaaa", 6);
        nativeDictionary.put("aaaaaaa", 7);
        nativeDictionary.put("aaaaaaaa", 8);
        nativeDictionary.put("aaaaaaaaa", 9);
        nativeDictionary.put("aaaaaaaaaa", 10);
        nativeDictionary.put("aaaaaaaaaaa", 11);
        nativeDictionary.put("aaaaaaaaaaaa", 12);
        nativeDictionary.put("aaaaaaaaaaaaa", 13);
        nativeDictionary.put("aaaaaaaaaaaaaa", 14);
        nativeDictionary.put("aaaaaaaaaaaaaaa", 15);
        nativeDictionary.put("aaaaaaaaaaaaaaaa", 16);
    }
}
