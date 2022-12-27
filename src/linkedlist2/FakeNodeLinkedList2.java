package linkedlist2;

import java.util.ArrayList;
import java.util.List;

public class FakeNodeLinkedList2 {
    private FakeHead head;
    private FakeTail tail;

    public FakeNodeLinkedList2() {
        head = new FakeHead();
        tail = new FakeTail();
        head.setNext(tail);
        head.setPrev(head);
    }

    public void addInTail(Node2 item) {
        if (item != null) {
            item.setPrev(tail.getPrev());
            item.setNext(tail);
            tail.getPrev().setNext(item);
            tail.setPrev(item);
        }
    }

    public Node2 find(int value) {
        if (head.getNext() == tail) {
            return null;
        }

        if (head.getNext() == tail.getPrev()) {
            if (head.getNext().getValue() == value) {
                return head.getNext();
            } else {
                return null;
            }
        }

        Node2 leftNode = head.getNext();
        Node2 rightNode = tail.getPrev();

        do {
            if (leftNode.getValue() == value) {
                return leftNode;
            }

            if (rightNode.getValue() == value) {
                return rightNode;
            }

            leftNode = leftNode.getNext();
            rightNode = rightNode.getPrev();

        } while (leftNode.getPrev() != rightNode.getNext() && leftNode.getPrev() != rightNode);

        return null;
    }

    public ArrayList<Node2> findAll(int value) {
        ArrayList<Node2> nodes = new ArrayList<>();

        if (head.getNext() == tail) {
            return nodes;
        }

        if (head.getNext() == tail.getPrev()) {
            if (head.getNext().getValue() == value) {
                nodes.add(head.getNext());
            }
            return nodes;
        }

        Node2 leftNode = head;
        Node2 rightNode = tail;

        do {
            if (leftNode.getValue() == value) {
                nodes.add(leftNode);
            }

            if (rightNode != leftNode && rightNode.getValue() == value) {
                nodes.add(rightNode);
            }

            leftNode = leftNode.getNext();
            rightNode = rightNode.getPrev();

        } while (leftNode.getPrev() != rightNode.getNext() && leftNode.getPrev() != rightNode);

        return nodes;
    }

    public boolean remove(int value) {
        if (head == null) {
            return false;
        }

        Node2 foundNode = find(value);

        if (foundNode != null) {
            removeNode(foundNode);

            return true;
        }

        return false;
    }

    public void removeAll(int _value) {
        List<Node2> list = findAll(_value);

        list.forEach(this::removeNode);
    }

    private void removeNode(Node2 node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
    }

    public void clear() {
        head.setNext(tail);
        head.setPrev(head);
    }

    public int count() {
        int n = 0;
        Node2 node = head.getNext();
        while (node != tail) {
            n++;
            node = node.getNext();
        }
        return n;
    }

    public void insertAfter(Node2 nodeAfter, Node2 nodeToInsert) {
        if (nodeToInsert == null) {
            return;
        }

        if (checkTheNodeIsContainedInList(nodeToInsert)) {
            return;
        }

        if (nodeAfter == null) {
            nodeToInsert.setPrev(head);
            nodeToInsert.setNext(head.getNext());
            head.getNext().setPrev(nodeToInsert);
            head.setNext(nodeToInsert);
            return;
        }

        Node2 node = head.getNext();
        while (node != null) {
            if (node == nodeAfter) {
                nodeToInsert.setPrev(node);
                nodeToInsert.setNext(node.getNext());
                node.getNext().setPrev(nodeToInsert);
                node.setNext(nodeToInsert);

                return;
            }
        }
    }

    private boolean checkTheNodeIsContainedInList(Node2 node) {
        Node2 current = head.getNext();
        while (current != tail) {
            if (current == node) {
                return true;
            }
            current = current.getNext();
        }

        return false;
    }
}

class Node2 {
    private int value;
    private Node2 next;
    private Node2 prev;

    public Node2(int _value) {
        value = _value;
        next = null;
        prev = null;
    }

    public int getValue() {
        return value;
    }

    public Node2 getNext() {
        return next;
    }

    public void setNext(Node2 next) {
        this.next = next;
    }

    public Node2 getPrev() {
        return prev;
    }

    public void setPrev(Node2 prev) {
        this.prev = prev;
    }
}

class FakeHead extends Node2 {

    public FakeHead() {
        super(Integer.MIN_VALUE);
    }

    @Override
    public Node2 getPrev() {
        return null;
    }

    @Override
    public void setPrev(Node2 prev) {
        super.setPrev(null);
    }
}

class FakeTail extends Node2 {

    public FakeTail() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public Node2 getNext() {
        return null;
    }

    @Override
    public void setNext(Node2 next) {
        super.setNext(null);
    }
}
