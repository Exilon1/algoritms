package nativecache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NativeCacheTest {

    private final NativeCache<Integer> nativeCache = new NativeCache<>(11, Integer.class);

    @Test
    void testCache() {
        fillCacheWithCollisions();

        nativeCache.get("a");
        nativeCache.get("a");
        nativeCache.get("a");
        nativeCache.get("a");
        nativeCache.get("a");

        nativeCache.get("aa");
        nativeCache.get("aa");
        nativeCache.get("aa");
        nativeCache.get("aa");
        nativeCache.get("aa");

        nativeCache.get("aaa");
        nativeCache.get("aaa");
        nativeCache.get("aaa");
        nativeCache.get("aaa");
        nativeCache.get("aaa");

        nativeCache.get("aaaa");
        nativeCache.get("aaaa");
        nativeCache.get("aaaa");

        nativeCache.get("aaaaa");
        nativeCache.get("aaaaa");
        nativeCache.get("aaaaa");
        nativeCache.get("aaaaa");
        nativeCache.get("aaaaa");
        nativeCache.get("aaaaa");

        nativeCache.get("aaaaaa");
        nativeCache.get("aaaaaa");
        nativeCache.get("aaaaaa");
        nativeCache.get("aaaaaa");
        nativeCache.get("aaaaaa");
        nativeCache.get("aaaaaa");
        nativeCache.get("aaaaaa");

        nativeCache.get("aaaaaaa");
        nativeCache.get("aaaaaaa");
        nativeCache.get("aaaaaaa");
        nativeCache.get("aaaaaaa");
        nativeCache.get("aaaaaaa");
        nativeCache.get("aaaaaaa");
        nativeCache.get("aaaaaaa");
        nativeCache.get("aaaaaaa");

        nativeCache.get("aaaaaaaa");
        nativeCache.get("aaaaaaaa");
        nativeCache.get("aaaaaaaa");
        nativeCache.get("aaaaaaaa");
        nativeCache.get("aaaaaaaa");
        nativeCache.get("aaaaaaaa");
        nativeCache.get("aaaaaaaa");
        nativeCache.get("aaaaaaaa");
        nativeCache.get("aaaaaaaa");

        nativeCache.get("aaaaaaaaa");
        nativeCache.get("aaaaaaaaa");
        nativeCache.get("aaaaaaaaa");
        nativeCache.get("aaaaaaaaa");
        nativeCache.get("aaaaaaaaa");
        nativeCache.get("aaaaaaaaa");
        nativeCache.get("aaaaaaaaa");
        nativeCache.get("aaaaaaaaa");
        nativeCache.get("aaaaaaaaa");
        nativeCache.get("aaaaaaaaa");

        nativeCache.get("aaaaaaaaaa");
        nativeCache.get("aaaaaaaaaa");
        nativeCache.get("aaaaaaaaaa");
        nativeCache.get("aaaaaaaaaa");
        nativeCache.get("aaaaaaaaaa");
        nativeCache.get("aaaaaaaaaa");
        nativeCache.get("aaaaaaaaaa");
        nativeCache.get("aaaaaaaaaa");
        nativeCache.get("aaaaaaaaaa");
        nativeCache.get("aaaaaaaaaa");
        nativeCache.get("aaaaaaaaaa");

        nativeCache.get("aaaaaaaaaaa");
        nativeCache.get("aaaaaaaaaaa");
        nativeCache.get("aaaaaaaaaaa");
        nativeCache.get("aaaaaaaaaaa");
        nativeCache.get("aaaaaaaaaaa");
        nativeCache.get("aaaaaaaaaaa");
        nativeCache.get("aaaaaaaaaaa");
        nativeCache.get("aaaaaaaaaaa");
        nativeCache.get("aaaaaaaaaaa");
        nativeCache.get("aaaaaaaaaaa");
        nativeCache.get("aaaaaaaaaaa");

        assertEquals(5, nativeCache.hits[nativeCache.find("a")]);
        assertEquals(5, nativeCache.hits[nativeCache.find("aa")]);
        assertEquals(5, nativeCache.hits[nativeCache.find("aaa")]);
        assertEquals(3, nativeCache.hits[nativeCache.find("aaaa")]);
        assertEquals(6, nativeCache.hits[nativeCache.find("aaaaa")]);
        assertEquals(7, nativeCache.hits[nativeCache.find("aaaaaa")]);
        assertEquals(8, nativeCache.hits[nativeCache.find("aaaaaaa")]);
        assertEquals(9, nativeCache.hits[nativeCache.find("aaaaaaaa")]);
        assertEquals(10, nativeCache.hits[nativeCache.find("aaaaaaaaa")]);
        assertEquals(11, nativeCache.hits[nativeCache.find("aaaaaaaaaa")]);
        assertEquals(11, nativeCache.hits[nativeCache.find("aaaaaaaaaaa")]);

        nativeCache.put("bbb", 17);

        assertEquals(0, nativeCache.hits[nativeCache.find("bbb")]);

        assertEquals(17, nativeCache.get("bbb"));
        assertEquals(1, nativeCache.hits[nativeCache.find("bbb")]);

        assertTrue(nativeCache.isKey("bbb"));
        assertFalse(nativeCache.isKey("aaaa"));
        assertNull(nativeCache.get("aaaa"));
    }

    private void fillCacheWithCollisions() {
        nativeCache.put("a", 1);
        nativeCache.put("aa", 2);
        nativeCache.put("aaa", 3);
        nativeCache.put("aaaa", 4);
        nativeCache.put("aaaaa", 5);
        nativeCache.put("aaaaaa", 6);
        nativeCache.put("aaaaaaa", 7);
        nativeCache.put("aaaaaaaa", 8);
        nativeCache.put("aaaaaaaaa", 9);
        nativeCache.put("aaaaaaaaaa", 10);
        nativeCache.put("aaaaaaaaaaa", 11);
    }
}
