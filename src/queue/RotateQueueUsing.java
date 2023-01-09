package queue;

public class RotateQueueUsing {

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        rotateQueue(queue, 2);

        while (queue.size() > 0) {
            System.out.println(queue.dequeue());
        }
    }

    public static void rotateQueue(Queue queue, int count) {
        for (int i = 0; i < count; i++) {
            queue.enqueue(queue.dequeue());
        }
    }
}
