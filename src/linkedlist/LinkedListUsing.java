package linkedlist;

public class LinkedListUsing {
    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();
        list1.addInTail(new Node(2));
        list1.addInTail(new Node(3));
        list1.addInTail(new Node(4));

        LinkedList list2 = new LinkedList();
        list2.addInTail(new Node(5));
        list2.addInTail(new Node(6));
        list2.addInTail(new Node(7));

        LinkedList list = merge(list1, list2);

        Node node = list.head;
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    public static LinkedList merge(LinkedList firstList, LinkedList secondList) {
        LinkedList target = new LinkedList();

        if (firstList.count() != secondList.count()) {
            return target;
        }

        Node firstNode = firstList.head;
        Node secondNode = secondList.head;
        while (firstNode != null) {
            target.addInTail(new Node(firstNode.value + secondNode.value));

            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }

        return target;
    }

}