package hashtable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashTableTest {

    private static final HashTable hashTable = new HashTable(17, 3);

    @AfterEach
    void clean() {
        hashTable.clear();
    }

    @Test
    void hashFunTest() {
        assertEquals(hashTable.hashFun("a "), hashTable.hashFun("a "));
        assertEquals(hashTable.hashFun("a"), hashTable.hashFun("a"));
        assertEquals(hashTable.hashFun("abc"), hashTable.hashFun("abc"));
        assertEquals(hashTable.hashFun("abcde"), hashTable.hashFun("abcde"));
        assertEquals(hashTable.hashFun("ab cde"), hashTable.hashFun("ab cde"));

        assertNotEquals(hashTable.hashFun("ab cde"), hashTable.hashFun("abc de"));
        assertNotEquals(hashTable.hashFun("abcde"), hashTable.hashFun("fghig"));
        assertNotEquals(hashTable.hashFun("abc"), hashTable.hashFun("def"));
        assertNotEquals(hashTable.hashFun("a"), hashTable.hashFun("b"));
        assertNotEquals(hashTable.hashFun("a "), hashTable.hashFun("b "));

        assertTrue(hashTable.hashFun("a ") >= 0);
        assertTrue(hashTable.hashFun("a") >= 0);
        assertTrue(hashTable.hashFun("abc") >= 0);
        assertTrue(hashTable.hashFun("abcde") >= 0);
        assertTrue(hashTable.hashFun("ab cde") >= 0);
        assertTrue(hashTable.hashFun("abc de") >= 0);
        assertTrue(hashTable.hashFun("fghig") >= 0);
        assertTrue(hashTable.hashFun("def") >= 0);
        assertTrue(hashTable.hashFun("b") >= 0);
        assertTrue(hashTable.hashFun("b ") >= 0);
    }

    @Test
    void seekSlotTest() {
        int indexOne = hashTable.seekSlot("a");
        assertTrue(indexOne >= 0);
        assertTrue(indexOne < hashTable.size);

        int indexTwo = hashTable.seekSlot("b");
        assertTrue(indexTwo >= 0);
        assertTrue(indexTwo < hashTable.size);

        assertNotEquals(indexOne, indexTwo);

        clean();

        fillTableWithCollisions();

        int indexThree = hashTable.seekSlot("b");
        assertTrue(indexThree >= 0);
        assertTrue(indexThree < hashTable.size);

        indexOne = hashTable.seekSlot("a");
        assertTrue(indexOne >= 0);
        assertTrue(indexOne < hashTable.size);

        clean();

        fillFullTableWithCollisions();

        indexThree = hashTable.seekSlot("b");
        assertEquals(-1, indexThree);

        indexOne = hashTable.seekSlot("a");
        assertTrue(indexOne >= 0);
        assertTrue(indexOne < hashTable.size);
    }

    @Test
    void putTest() {
        int indexOne = hashTable.put("a");
        assertTrue(indexOne >= 0);
        assertTrue(indexOne < hashTable.size);

        int indexTwo = hashTable.find("a");
        assertEquals(indexOne, indexTwo);

        clean();

        fillTableWithCollisions();

        indexOne = hashTable.put("b");
        assertTrue(indexOne >= 0);
        assertTrue(indexOne < hashTable.size);

        indexTwo = hashTable.find("b");
        assertEquals(indexOne, indexTwo);

        int indexThree = hashTable.put("bb");
        assertEquals(-1, indexThree);

        int indexFour = hashTable.find("bb");
        assertEquals(-1, indexFour);
    }

    @Test
    void findTest() {
        int indexOne = hashTable.find("a");
        assertEquals(-1, indexOne);

        fillFullTableWithCollisions();

        int indexTwo = hashTable.find("a");
        assertTrue(indexTwo >= 0);
        assertTrue(indexTwo < hashTable.size);

        indexTwo = hashTable.find("aaaaaaaaaaaaaaaaa");
        assertTrue(indexTwo >= 0);
        assertTrue(indexTwo < hashTable.size);

        indexTwo = hashTable.find("bb");
        assertEquals(-1, indexTwo);
    }

    private void fillTableWithCollisions() {
        hashTable.put("a");
        hashTable.put("aa");
        hashTable.put("aaa");
        hashTable.put("aaaa");
        hashTable.put("aaaaa");
        hashTable.put("aaaaaa");
        hashTable.put("aaaaaaa");
        hashTable.put("aaaaaaaa");
        hashTable.put("aaaaaaaaa");
        hashTable.put("aaaaaaaaaa");
        hashTable.put("aaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaaaaaa");
    }

    private void fillFullTableWithCollisions() {
        hashTable.put("a");
        hashTable.put("aa");
        hashTable.put("aaa");
        hashTable.put("aaaa");
        hashTable.put("aaaaa");
        hashTable.put("aaaaaa");
        hashTable.put("aaaaaaa");
        hashTable.put("aaaaaaaa");
        hashTable.put("aaaaaaaaa");
        hashTable.put("aaaaaaaaaa");
        hashTable.put("aaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaaaaaa");
        hashTable.put("aaaaaaaaaaaaaaaaa");
    }
}
