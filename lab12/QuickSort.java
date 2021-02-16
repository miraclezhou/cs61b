import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        for (Item i : unsorted) {
            if (i.compareTo(pivot) < 0) {
                less.enqueue(i);
            } else if (i.compareTo(pivot) > 0) {
                greater.enqueue(i);
            } else {
                equal.enqueue(i);
            }
        }
        if (less.size() < 2 && greater.size() < 2) {
            unsorted = catenate(catenate(less, equal), greater);
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Check empty queue.
        if (items.isEmpty() || items.size() == 1) {
            return items;
        }
        // If given queue's size is 1, it is already sorted, just return.
        if (items.size() == 1) {
            return items;
        }
        /**
         *  Recursively do the following work:
         *  1. Randomly select a pivot, partition the unsorted-queue in to three queues: less,
         *     equal and greater.
         *  2. If size of less and greater are both less than 2, it means less and greater are
         *     already sorted, just catenate less, equal and greater, then return.
         *  3. Else, it means at least one of them is unsorted(because its size is more than 1),
         *     quickSort the unsorted queues, then catenate less, equal and greater, then return.
         */
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();
        Item pivot = getRandomItem(items);
        partition(items, pivot, less, equal, greater);
        // After partition, the three queues are already sorted, just catenate and return.
        if (less.size() < 2 && greater.size() < 2) {
            items = catenate(catenate(less, equal), greater);
            return items;
        }
        // QuickSort queue of less and greater.
        less = quickSort(less);
        greater = quickSort(greater);
        // After quickSort less and greater, catenate the three parts and return.
        Queue<Item> leftHalf = catenate(less, equal);
        Queue<Item> sorted = catenate(leftHalf, greater);
        return sorted;
    }


    public static void main(String[] args) {
        Queue<Integer> mq = new Queue<>();
        mq.enqueue(2);
        mq.enqueue(4);
        mq.enqueue(20);
        mq.enqueue(7);
        mq.enqueue(3);
        mq.enqueue(9);
        mq.enqueue(19);
        mq.enqueue(1);
        mq.enqueue(8);
        mq.enqueue(5);
        mq.enqueue(6);
        mq.enqueue(12);
        System.out.println("Test quickSort: ");
        System.out.println("Before sorting: " + mq);
        mq = quickSort(mq);
        System.out.println("After sorting: " + mq);
    }
}
