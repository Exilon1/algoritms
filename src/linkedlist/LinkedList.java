package linkedlist;

import java.util.*;

public class LinkedList {
    public Node head;
    public Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public Node findPrevious(int value) {
        Node node = this.head;
        Node prevNode = null;
        while (node != null) {
            if (node.value == value) {
                return prevNode;
            }
            prevNode = node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<>();
        Node node = this.head;
        while (node != null) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(int _value) {
        if (this.head == null) {
            return false;
        }

        Node prevNode = findPrevious(_value);
        if (prevNode != null) {
            Node node = prevNode.next;
            prevNode.next = node.next;
            if (prevNode.next == null) {
                this.tail = prevNode;
            }
            return true;
        } else if (head.value == _value) {
            this.head = this.head.next;
            if (this.head == null) {
                this.tail = null;
            }

            return true;
        }
        return false;
    }

    public void removeAll(int _value) {
        Node node = this.head;
        Node prevNode = null;
        while (node != null) {
            if (node.value == _value) {
                if (prevNode != null) {
                    prevNode.next = node.next;
                    if (prevNode.next == null) {
                        this.tail = prevNode;
                    }
                } else {
                    this.head = node.next;
                    if (this.head == null) {
                        this.tail = null;
                    }
                }
                node = node.next;
                continue;
            }

            prevNode = node;
            node = node.next;
        }
    }

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    public int count() {
        int n = 0;
        Node node = this.head;
        while (node != null) {
            n++;
            node = node.next;
        }
        return n;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeToInsert == null) {
            return;
        }

        if (checkTheNodeIsContainedInList(_nodeToInsert)) {
            return;
        }

        Node node =  this.head;
        while (node != null) {
            if (node.equals(_nodeAfter)) {
                _nodeToInsert.next = node.next;
                node.next = _nodeToInsert;
                if (_nodeToInsert.next == null) {
                    this.tail = _nodeToInsert;
                }
                return;
            }
            node = node.next;
        }
    }

    private boolean checkTheNodeIsContainedInList(Node node) {
        Node current =  this.head;
        while (current != null) {
            if (current.equals(node)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int _value) {
        value = _value;
        next = null;
    }
}
