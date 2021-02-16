
import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> singleIQ = new Queue<>();
        int numOfItems = items.size();
        Queue<Item>[] queues = new Queue[numOfItems];
        int index = 0;
        for (Item i : items) {
            queues[index] = new Queue<>();
            queues[index].enqueue(i);
            singleIQ.enqueue(queues[index]);
            index += 1;
        }
        return singleIQ;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    // Not using the provided helper method getMin();
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!

        Queue<Item> q = new Queue<>();
        /** If one of the two queues is null, return the other queue. */
        if (q1.size() == 0) {
            return q2;
        }
        if (q2.size() == 0) {
            return q1;
        }
        /** Using iterator to traverse the queue. */
        Iterator<Item> i1 = q1.iterator();
        Iterator<Item> i2 = q2.iterator();
        Item item1 = i1.next();
        Item item2 = i2.next();
        while (true) {
            /** Compare item1 with item2, enqueue the smaller to q. */
            if (item1.compareTo(item2) < 0) {
                q.enqueue(item1);
                /** If i1 has next item, get it.
                 *  If not, it means i1 goes its end, enqueue all i2's remaining items to q.
                 *  Once finished, sorting is done, break out of the loop.
                 */
                if (i1.hasNext()) {
                    item1 = i1.next();
                } else {
                    q.enqueue(item2);
                    while (i2.hasNext()) {
                        q.enqueue(i2.next());
                    }
                    break;
                }
            } else {
                q.enqueue(item2);
                if (i2.hasNext()) {
                    item2 = i2.next();
                } else {
                    q.enqueue(item1);
                    while (i1.hasNext()) {
                        q.enqueue(i1.next());
                    }
                    break;
                }
            }
        }
        return q;
    }

    // Using provided helper method getMin().
//    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
//            Queue<Item> q1, Queue<Item> q2) {
//        if (q1.isEmpty() && q2.isEmpty()) {
//            throw new IllegalArgumentException("The two queues both cannot be empty. ");
//        }
//        Queue<Item> q = new Queue<>();
//        int size = q1.size() + q2.size();
//        int i = 0;
//        while (i != size) {
//            Item item = getMin(q1, q2);
//            q.enqueue(item);
//            i += 1;
//        }
//        return q;
//    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Check empty queue.
        if (items.isEmpty() || items.size() == 1) {
            return items;
        }
        Queue<Queue<Item>> singleQ = makeSingleItemQueues(items);
        /** Always dequeue the first two queues, sort them and get a new sorted queue,
         *  then enqueue to the rear of singleQ.
         *  Because queue is FirstInFirstOut, one-item-queue will first finish sorting,
         *  and then enqueue every new two-item-queue. Next, two-item-queue will start
         *  to sort, then four-item-queue will start to sort, and so on.
          */
        while (singleQ.size() > 1) {
            Queue<Item> q1 = singleQ.dequeue();
            Queue<Item> q2 = singleQ.dequeue();
            Queue<Item> q = mergeSortedQueues(q1, q2);
            singleQ.enqueue(q);
        }
        items = singleQ.dequeue();
        return items;
    }

    public static void main(String[] args) {
        Queue<String> students = new Queue<>();

        mergeSort(students);
        students.enqueue("Bob");
        students.enqueue("Miracle");
        students.enqueue("Ceb");
        students.enqueue("Rtz");
        students.enqueue("Sumail");
        students.enqueue("Sccc");
        System.out.println("Test makeSingleItemQueues: ");
        System.out.println("Items Queue: " + students);
        System.out.println("SingleItemQueue: " + makeSingleItemQueues(students));
        System.out.println("-----------------------------");
        Queue<Integer> q1 = new Queue<>();
        q1.enqueue(2);
        q1.enqueue(3);
        q1.enqueue(6);
        q1.enqueue(7);
        Queue<Integer> q2 = new Queue<>();
        q2.enqueue(4);
        q2.enqueue(5);
        q2.enqueue(9);
        q2.enqueue(10);
        q2.enqueue(11);
        System.out.println("Test mergeSortedQueues: ");
        System.out.println("q1: " + q1);
        System.out.println("q2: " + q2);
        System.out.println("Merge sort q1 and q2: " + mergeSortedQueues(q1, q2));
        System.out.println("-----------------------------");
        Queue<Integer> mq = new Queue<>();
        mq.enqueue(2);
        mq.enqueue(4);
        mq.enqueue(7);
        mq.enqueue(3);
        mq.enqueue(9);
        mq.enqueue(1);
        mq.enqueue(8);
        mq.enqueue(5);
        mq.enqueue(6);
        System.out.println("Test mergeSort: ");
        System.out.println("Before sorting: " + mq);
        mq = mergeSort(mq);
        System.out.println("After sorting: " + mq);
    }
}
