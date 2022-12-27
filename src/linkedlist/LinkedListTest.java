package linkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class LinkedListTest {

    private LinkedList list = new LinkedList();


    @AfterEach
    void clean() {
        list.clear();
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

        fillDifferentElements();

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

        fillDifferentElements();

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

        fillElementsWithRepetitionsAtTheBeginning();

        list.removeAll(1);
        assertEquals(3, list.count());
        assertEquals(3, list.head.value);

        clean();

        fillElementsWithRepetitionsAtTheMiddle();

        list.removeAll(2);
        assertEquals(3, list.count());
        assertEquals(1, list.head.value);
        assertEquals(5, list.tail.value);

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

        fillDifferentElements();
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

        clean();

        fillDifferentElements();

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

        fillElementsWithRepetitionsAtTheBeginning();

        nodeList = list.findAll(1);
        assertEquals(2, nodeList.size());
        assertEquals(1, nodeList.get(0).value);
        assertEquals(1, nodeList.get(1).value);

        clean();

        fillElementsWithRepetitionsAtTheMiddle();

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

        fillDifferentElements();

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

        list.insertAfter(nodeFromList, new Node(8));
        assertEquals(3, list.count());
        assertEquals(8, list.tail.value);

        list.insertAfter(nodeToInsert, nodeToInsert);
        assertEquals(3, list.count());
        assertEquals(list.tail, nodeToInsert);

        clean();

        fillDifferentElements();

        nodeFromList = list.find(2);

        list.insertAfter(nodeFromList, nodeToInsert);
        assertEquals(6, list.count());
        assertEquals(nodeToInsert, list.find(7));

        list.remove(7);

        nodeFromList = list.find(5);

        list.insertAfter(nodeFromList, nodeToInsert);
        assertEquals(6, list.count());
        assertEquals(nodeToInsert, list.find(7));
    }


    void fillSingleElement() {
        list.addInTail(new Node(3));
    }

    void fillDifferentElements() {
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(3));
        list.addInTail(new Node(4));
        list.addInTail(new Node(5));
    }

    void fillElementsWithRepetitionsAtTheBeginning() {
        list.addInTail(new Node(1));
        list.addInTail(new Node(1));
        list.addInTail(new Node(3));
        list.addInTail(new Node(4));
        list.addInTail(new Node(5));
    }

    void fillElementsWithRepetitionsAtTheMiddle() {
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(2));
        list.addInTail(new Node(4));
        list.addInTail(new Node(5));
    }

    void fillElementsWithRepetitionsAtTheEnd() {
        list.addInTail(new Node(1));
        list.addInTail(new Node(2));
        list.addInTail(new Node(3));
        list.addInTail(new Node(5));
        list.addInTail(new Node(5));
    }

}