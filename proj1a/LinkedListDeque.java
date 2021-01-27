public class LinkedListDeque<T> {

    /* invarients: 1.sentinel's next is the first node, its prev is the last node.
     */

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;


    // Creates an empty linked list deque.
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
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
                T pItem = get(i);
                System.out.print(pItem + " ");
            }
        }
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        T rFirst = null;
        if(!isEmpty()) {
            rFirst = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
        }
        return rFirst;
    }

    // Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        T rLast = null;
        if(!isEmpty()) {
            rLast = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
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

        Node p = sentinel.next;
        while(index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    // Same as get, but uses recursion.
    public T getRecursive(int index) {
        // Check the index.
        if(index < 0 && index >= size) {
            return null;
        }
        // Check the empty deque.
        if(isEmpty()) {
            return null;
        }
        Node p = sentinel.next;
        return getRecursiveHelper(p, index);
    }

    private T getRecursiveHelper(Node n, int index) {
        if(index == 0) {
            return n.item;
        }
        return getRecursiveHelper(n.next, index - 1);
    }

    /*
    public static void main(String[] args) {
        LinkedListDeque<String> L = new LinkedListDeque<>();
        L.addFirst("a");
        L.addFirst("b");
        L.addLast("cc");
        L.addLast("dd");

        System.out.println(L.getRecursive(2));
        System.out.println(L.removeLast());
    }
    */

}
