public class ArrayDeque<T> {

    private T[] items;
    private int size;

    private int head;
    private int rear;

    // Creates an empty linked list deque.
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        head = 3;
        rear = 4;
    }

    // Given an index, return the relative index of circle array.
    private int getCircleIndex(int index) {
        if (index < 0) {
            index += items.length;
        }
        return index % items.length;
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if(size == items.length) {
            resize(items.length * 2);
        }
        items[head] = item;
        head = getCircleIndex(head - 1);
        size += 1;
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if(size == items.length) {
            resize(items.length * 2);
        }
        items[rear] = item;
        rear = getCircleIndex(rear + 1);
        size += 1;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the deque.
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        if(!isEmpty()) {
            for(int i = 0; i < size; i++) {
                System.out.print(get(i) + " ");
            }
        }
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        T rFirst = null;
        if(!isEmpty()) {
            int first = getCircleIndex(head + 1);
            rFirst = items[first];
            items[first] = null;
            head = first;
            size -= 1;
            if(items.length > 8 && usageFactor() == 0.25) {
                resize(items.length / 2);
            }
        }
        return rFirst;
    }

    // Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        T rLast = null;
        if(!isEmpty()) {
            int last = getCircleIndex(rear - 1);
            rLast = items[last];
            items[last] = null;
            rear = last;
            size -= 1;
            if(items.length > 8 && usageFactor() == 0.25) {
                resize(items.length / 2);
            }
        }
        return rLast;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        // Check the index.
        if(index < 0 && index >= size) {
            return null;
        }
        // Check the empty deque.
        if(isEmpty()) {
            return null;
        }
        int arrayDequeIndex = getCircleIndex(head + 1 + index);
        return items[arrayDequeIndex];
    }

    // Get the usage factor of items.
    private double usageFactor() {
        return (double) size / items.length;
    }

    // Resize the array of items.
    private void resize(int capacity) {
        int first = getCircleIndex(head + 1);
        int last = getCircleIndex(rear - 1);
        T[] a = (T[]) new Object[capacity];
        if (first < last) {
            System.arraycopy(items, first, a, 0, size);
        } else {
            int partNum = items.length - first;
            System.arraycopy(items, first, a, 0, partNum);
            System.arraycopy(items, 0, a, partNum, size - partNum);
        }
        head = capacity - 1;
        rear = size;
        items = a;
    }

    /*
    public static void main(String[] args) {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        for (int i = 0; i < 20; i ++) {
            A.addFirst(i);
        }
        for (int i = 0; i < 20; i ++) {
            System.out.println(A.removeLast());
        }
        //A.printDeque();
    }
    */

}
