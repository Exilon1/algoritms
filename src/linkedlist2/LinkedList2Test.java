package linkedlist2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedList2Test {

    private static final LinkedList2 list = new LinkedList2();


    @AfterEach
    void clean() {
        list.clear();
    }

    @Test
    void findTest() {
        assertNull(list.find(3));

        fillSingleElement();

        assertNull(list.find(0));
        assertEquals(3, list.find(3).value);

        clean();

        fillDifferentFirstPreset();

        assertNull(list.find(0));
        assertEquals(1, list.find(1).value);
        assertEquals(3, list.find(3).value);
        assertEquals(5, list.find(5).value);

        clean();

        fillDifferentSecondPreset();

        assertNull(list.find(0));
        assertEquals(2, list.find(2).value);
    }

    @Test
    void removeTest() {
        assertFalse(list.remove(1));

        fillSingleElement();

        assertFalse(list.remove(1));
        assertEquals(list.head, list.tail);
        assertEquals(3, list.head.value);

        assertTrue(list.remove(3));
        assertNull(list.head);
        assertNull(list.tail);

        clean();

        fillDifferentFirstPreset();

        assertFalse(list.remove(0));
        assertEquals(5, list.count());

        assertTrue(list.remove(1));
        assertNull(list.find(1));
        assertEquals(4, list.count());
        assertEquals(2, list.head.value);

        assertTrue(list.remove(5));
        assertNull(list.find(5));
        assertEquals(3, list.count());
        assertEquals(4, list.tail.value);

        assertTrue(list.remove(3));
        assertNull(list.find(3));
        assertEquals(2, list.count());
        assertEquals(2, list.head.value);
        assertEquals(4, list.tail.value);

        clean();

        fillDifferentSecondPreset();

        assertTrue(list.remove(2));
        assertNull(list.find(2));
        assertEquals(3, list.count());
        assertEquals(1, list.head.value);
        assertEquals(4, list.tail.value);
    }

    @Test
    void removeAllTest() {
        fillSingleElement();

        list.removeAll(1);
        assertEquals(list.head, list.tail);
        assertEquals(3, list.head.value);

        list.removeAll(3);
        assertNull(list.head);
        assertNull(list.tail);

        clean();

        fillDifferentFirstPreset();

        list.removeAll(0);
        assertEquals(5, list.count());

        list.removeAll(1);
        assertNull(list.find(1));
        assertEquals(4, list.count());
        assertEquals(2, list.head.value);

        list.removeAll(5);
        assertNull(list.find(5));
        assertEquals(3, list.count());
        assertEquals(4, list.tail.value);

        list.removeAll(3);
        assertNull(list.find(3));
        assertEquals(2, list.count());
        assertEquals(2, list.head.value);
        assertEquals(4, list.tail.value);

        clean();

        fillDifferentSecondPreset();

        list.removeAll(2);
        assertNull(list.find(2));
        assertEquals(3, list.count());
        assertEquals(1, list.head.value);
        assertEquals(4, list.tail.value);

        clean();

        fillElementsWithRepetitionsAtTheBeginning();

        list.removeAll(1);
        assertNull(list.find(1));
        assertEquals(3, list.count());
        assertEquals(3, list.head.value);

        clean();

        fillElementsWithRepetitionsAtTheMiddleFirstPreset();

        list.removeAll(2);
        assertEquals(2, list.count());
        assertEquals(1, list.head.value);
        assertEquals(5, list.tail.value);

        clean();

        fillElementsWithRepetitionsAtTheMiddleSecondPreset();

        list.removeAll(2);
        assertEquals(2, list.count());
        assertEquals(1, list.head.value);
        assertEquals(4, list.tail.value);

        clean();

        fillElementsWithRepetitionsAtTheEnd();

        list.removeAll(5);
        assertEquals(3, list.count());
        assertEquals(3, list.tail.value);
    }

    @Test
    void cleanTest() {
        fillSingleElement();
        list.clear();

        assertNull(list.head);
        assertNull(list.tail);

        fillDifferentFirstPreset();
        list.clear();

        assertNull(list.head);
        assertNull(list.tail);
    }

    @Test
    void findAllTest() {
        assertTrue(list.findAll(0).isEmpty());

        fillSingleElement();

        ArrayList<Node> nodeList;

        nodeList = list.findAll(1);
        assertEquals(0, nodeList.size());

        nodeList = list.findAll(3);
        assertEquals(1, nodeList.size());
        assertEquals(3, nodeList.get(0).value);

        clean();

        fillDifferentFirstPreset();

        nodeList = list.findAll(0);
        assertEquals(0, nodeList.size());

        nodeList = list.findAll(1);
        assertEquals(1, nodeList.size());
        assertEquals(1, nodeList.get(0).value);

        nodeList = list.findAll(3);
        assertEquals(1, nodeList.size());
        assertEquals(3, nodeList.get(0).value);

        nodeList = list.findAll(5);
        assertEquals(1, nodeList.size());
        assertEquals(5, nodeList.get(0).value);

        clean();

        fillDifferentSecondPreset();

        nodeList = list.findAll(2);
        assertEquals(1, nodeList.size());
        assertEquals(2, nodeList.get(0).value);

        clean();

        fillElementsWithRepetitionsAtTheBeginning();

        nodeList = list.findAll(1);
        assertEquals(2, nodeList.size());
        assertEquals(1, nodeList.get(0).value);
        assertEquals(1, nodeList.get(1).value);

        clean();

        fillElementsWithRepetitionsAtTheMiddleFirstPreset();

        nodeList = list.findAll(2);
        assertEquals(3, nodeList.size());
        assertEquals(2, nodeList.get(0).value);
        assertEquals(2, nodeList.get(1).value);
        assertEquals(2, nodeList.get(2).value);

        clean();

        fillElementsWithRepetitionsAtTheMiddleSecondPreset();

        nodeList = list.findAll(2);
        assertEquals(2, nodeList.size());
        assertEquals(2, nodeList.get(0).value);
        assertEquals(2, nodeList.get(1).value);

        clean();

        fillElementsWithRepetitionsAtTheEnd();

        nodeList = list.findAll(5);
        assertEquals(2, nodeList.size());
        assertEquals(5, nodeList.get(0).value);
        assertEquals(5, nodeList.get(1).value);
    }

    @Test
    void countTest() {
        assertEquals(0, list.count());

        fillSingleElement();

        assertEquals(1, list.count());

        clean();

        fillDifferentFirstPreset();

        assertEquals(5, list.count());
    }

    @Test
    void insertAfterTest() {
        Node someNode = new Node(3);
        Node nodeToInsert = new Node(7);
        Node nodeFromList;

        list.insertAfter(someNode, nodeToInsert);
        assertEquals(0, list.count());
        assertNull(list.head);
        assertNull(list.tail);

        list.insertAfter(someNode, null);
        assertEquals(0, list.count());
        assertNull(list.head);
        assertNull(list.tail);

        list.insertAfter(null, nodeToInsert);
        assertEquals(1, list.count());
        assertEquals(7, list.head.value);
        assertEquals(list.head, list.tail);

        clean();

        fillSingleElement();

        nodeFromList = list.find(3);

        list.insertAfter(someNode, nodeToInsert);
        assertEquals(1, list.count());
        assertEquals(list.head, list.tail);

        list.insertAfter(someNode, null);
        assertEquals(1, list.count());
        assertEquals(list.head, list.tail);

        list.insertAfter(nodeFromList, null);
        assertEquals(1, list.count());
        assertEquals(list.head, list.tail);

        list.insertAfter(null, nodeToInsert);
        assertEquals(2, list.count());
        assertEquals(list.head, nodeToInsert);
        assertEquals(list.tail, nodeFromList);

        nodeToInsert = new Node(8);

        list.insertAfter(nodeFromList, nodeToInsert);
        assertEquals(3, list.count());
        assertEquals(list.tail, nodeToInsert);

        list.insertAfter(nodeToInsert, nodeToInsert);
        assertEquals(3, list.count());
        assertEquals(list.tail, nodeToInsert);

        clean();

        fillDifferentFirstPreset();

        nodeFromList = list.find(2);

        list.insertAfter(nodeFromList, nodeToInsert);
        assertEquals(6, list.count());
        assertEquals(nodeToInsert, list.find(8));

        list.remove(8);

        nodeFromList = list.find(5);

        list.insertAfter(nodeFromList, nodeToInsert);
        assertEquals(6, list.count());
        assertEquals(nodeToInsert, list.find(8));
    }

    void fillSingleElement() {
        list.addInTail(new Node(3));
    }

    static void fillDifferentFirstPreset() {
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(3));
        list.addInTail(new Node(4));
        list.addInTail(new Node(5));
    }

    void fillDifferentSecondPreset() {
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(3));
        list.addInTail(new Node(4));
    }

    static void fillElementsWithRepetitionsAtTheBeginning() {
        list.addInTail(new Node(1));
        list.addInTail(new Node(1));
        list.addInTail(new Node(3));
        list.addInTail(new Node(4));
        list.addInTail(new Node(5));
    }

    void fillElementsWithRepetitionsAtTheMiddleFirstPreset() {
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(2));
        list.addInTail(new Node(2));
        list.addInTail(new Node(5));
    }

    void fillElementsWithRepetitionsAtTheMiddleSecondPreset() {
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(2));
        list.addInTail(new Node(4));
    }

    void fillElementsWithRepetitionsAtTheEnd() {
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(3));
        list.addInTail(new Node(5));
        list.addInTail(new Node(5));
    }
}
