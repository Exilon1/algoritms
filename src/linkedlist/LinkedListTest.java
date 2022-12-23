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
        assertEquals(list.head.value, 3);

        assertTrue(list.remove(3));
        assertNull(list.head);
        assertNull(list.tail);

        clean();

        fillDifferentElements();

        assertFalse(list.remove(0));
        assertEquals(list.count(), 5);

        assertTrue(list.remove(1));
        assertNull(list.find(1));
        assertEquals(list.count(), 4);
        assertEquals(list.head.value, 2);

        assertTrue(list.remove(5));
        assertNull(list.find(5));
        assertEquals(list.count(), 3);
        assertEquals(list.tail.value, 4);

        assertTrue(list.remove(3));
        assertNull(list.find(3));
        assertEquals(list.count(), 2);
        assertEquals(list.head.value, 2);
        assertEquals(list.tail.value, 4);
    }

    @Test
    void removeAllTest() {
        fillSingleElement();

        list.removeAll(1);
        assertEquals(list.head, list.tail);
        assertEquals(list.head.value, 3);

        list.removeAll(3);
        assertNull(list.head);
        assertNull(list.tail);

        clean();

        fillDifferentElements();

        list.removeAll(0);
        assertEquals(list.count(), 5);

        list.removeAll(1);
        assertNull(list.find(1));
        assertEquals(list.count(), 4);
        assertEquals(list.head.value, 2);

        list.removeAll(5);
        assertNull(list.find(5));
        assertEquals(list.count(), 3);
        assertEquals(list.tail.value, 4);

        list.removeAll(3);
        assertNull(list.find(3));
        assertEquals(list.count(), 2);
        assertEquals(list.head.value, 2);
        assertEquals(list.tail.value, 4);

        clean();

        fillElementsWithRepetitionsAtTheBeginning();

        list.removeAll(1);
        assertEquals(list.count(), 3);
        assertEquals(list.head.value, 3);

        clean();

        fillElementsWithRepetitionsAtTheMiddle();

        list.removeAll(2);
        assertEquals(list.count(), 3);
        assertEquals(list.head.value, 1);
        assertEquals(list.tail.value, 5);

        clean();

        fillElementsWithRepetitionsAtTheEnd();

        list.removeAll(5);
        assertEquals(list.count(), 3);
        assertEquals(list.tail.value, 3);
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
        fillSingleElement();

        ArrayList<Node> nodeList;

        nodeList = list.findAll(1);
        assertEquals(nodeList.size(), 0);

        nodeList = list.findAll(3);
        assertEquals(nodeList.size(), 1);

        clean();

        fillDifferentElements();

        nodeList = list.findAll(0);
        assertEquals(nodeList.size(), 0);

        nodeList = list.findAll(1);
        assertEquals(nodeList.size(), 1);
        assertEquals(nodeList.get(0).value, 1);

        nodeList = list.findAll(3);
        assertEquals(nodeList.size(), 1);
        assertEquals(nodeList.get(0).value, 3);

        nodeList = list.findAll(5);
        assertEquals(nodeList.size(), 1);
        assertEquals(nodeList.get(0).value, 5);

        clean();

        fillElementsWithRepetitionsAtTheBeginning();

        nodeList = list.findAll(1);
        assertEquals(nodeList.size(), 2);
        assertEquals(nodeList.get(0).value, 1);
        assertEquals(nodeList.get(1).value, 1);

        clean();

        fillElementsWithRepetitionsAtTheMiddle();

        nodeList = list.findAll(2);
        assertEquals(nodeList.size(), 2);
        assertEquals(nodeList.get(0).value, 2);
        assertEquals(nodeList.get(1).value, 2);

        clean();

        fillElementsWithRepetitionsAtTheEnd();

        nodeList = list.findAll(5);
        assertEquals(nodeList.size(), 2);
        assertEquals(nodeList.get(0).value, 5);
        assertEquals(nodeList.get(1).value, 5);
    }

    @Test
    void countTest() {
        assertEquals(list.count(), 0);

        fillSingleElement();

        assertEquals(list.count(), 1);

        clean();

        fillDifferentElements();

        assertEquals(list.count(), 5);
    }

    @Test
    void insertAfterTest() {
        Node someNode = new Node(3);
        Node nodeToInsert = new Node(7);
        Node nodeFromList;

        list.insertAfter(null, nodeToInsert);
        assertEquals(list.count(), 0);
        assertNull(list.head);
        assertNull(list.tail);

        list.insertAfter(someNode, nodeToInsert);
        assertEquals(list.count(), 0);
        assertNull(list.head);
        assertNull(list.tail);

        list.insertAfter(someNode, null);
        assertEquals(list.count(), 0);
        assertNull(list.head);
        assertNull(list.tail);

        clean();

        fillSingleElement();

        nodeFromList = list.find(3);

        list.insertAfter(null, nodeToInsert);
        assertEquals(list.count(), 1);
        assertEquals(list.head, list.tail);

        list.insertAfter(someNode, nodeToInsert);
        assertEquals(list.count(), 1);
        assertEquals(list.head, list.tail);

        list.insertAfter(someNode, null);
        assertEquals(list.count(), 1);
        assertEquals(list.head, list.tail);

        list.insertAfter(nodeFromList, null);
        assertEquals(list.count(), 1);
        assertEquals(list.head, list.tail);

        list.insertAfter(nodeFromList, nodeToInsert);
        assertEquals(list.count(), 2);
        assertEquals(list.tail, nodeToInsert);

        list.insertAfter(nodeToInsert, nodeToInsert);
        assertEquals(list.count(), 2);
        assertEquals(list.tail, nodeToInsert);

        clean();

        fillDifferentElements();

        nodeFromList = list.find(2);

        list.insertAfter(nodeFromList, nodeToInsert);
        assertEquals(list.count(), 6);
        assertEquals(list.find(7), nodeToInsert);

        list.remove(7);

        nodeFromList = list.find(5);

        list.insertAfter(nodeFromList, nodeToInsert);
        assertEquals(list.count(), 6);
        assertEquals(list.find(7), nodeToInsert);
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