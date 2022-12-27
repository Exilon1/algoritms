package linkedlist2;

import java.util.ArrayList;
import java.util.List;

public class LinkedList2 {
    public Node head;
    public Node tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value) {
        if (head == null) {
            return null;
        }

        if (head == tail) {
            if (head.value == _value) {
                return head;
            } else {
                return null;
            }
        }

        Node leftNode = head;
        Node rightNode = tail;

        do {
            if (leftNode.value == _value) {
                return leftNode;
            }

            if (rightNode.value == _value) {
                return rightNode;
            }

            leftNode = leftNode.next;
            rightNode = rightNode.prev;
        } while (leftNode.prev != rightNode.next && leftNode.prev != rightNode);

        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<>();

        if (head == null) {
            return nodes;
        }

        if (head == tail) {
            if (head.value == _value) {
                nodes.add(head);
            }
            return nodes;
        }

        Node leftNode = head;
        Node rightNode = tail;

        do {
            if (leftNode.value == _value) {
                nodes.add(leftNode);
            }

            if (rightNode != leftNode && rightNode.value == _value) {
                nodes.add(rightNode);
            }

            leftNode = leftNode.next;
            rightNode = rightNode.prev;
        } while (leftNode.prev != rightNode.next && leftNode.prev != rightNode);

        return nodes;
    }

    public boolean remove(int _value) {
        if (head == null) {
            return false;
        }

        Node foundNode = find(_value);

        if (foundNode != null) {
            removeNode(foundNode);

            return true;
        }

        return false;
    }

    public void removeAll(int _value) {
        List<Node> list = findAll(_value);

        list.forEach(this::removeNode);
    }

    private void removeNode(Node node) {
        if (node.prev == null && node.next == null) {
            head = null;
            tail = null;
        } else if (node.prev == null) {
            head = node.next;
            head.prev = null;
        } else if (node.next == null) {
            tail = node.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.prev = null;
        node.next = null;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public int count() {
        int n = 0;
        Node node = head;
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

        if (_nodeAfter == null) {
            _nodeToInsert.next = head;
            if (head == null) {
                tail = _nodeToInsert;
            } else {
                head.prev = _nodeToInsert;
            }
            head = _nodeToInsert;

            return;
        }

        Node node = head;
        while (node != null) {
            if (node.equals(_nodeAfter)) {
                _nodeToInsert.next = node.next;
                _nodeToInsert.prev = node;

                if (_nodeToInsert.next == null) {
                    tail = _nodeToInsert;
                } else {
                    node.next.prev = _nodeToInsert;
                }
                node.next = _nodeToInsert;

                return;
            }
            node = node.next;
        }
    }

    private boolean checkTheNodeIsContainedInList(Node node) {
        Node current = head;
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
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }
}
