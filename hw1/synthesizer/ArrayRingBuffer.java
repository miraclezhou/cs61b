package synthesizer;


import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {

        /** throw exception if buffer is full. */
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        /** Set last = 0 if the index equals capacity. */
        if (last == this.capacity) {
            last = 0;
        }

        rb[last] = x;
        last += 1;
        this.fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {

        T deItem = peek();
        first += 1;
        this.fillCount -= 1;
        return deItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        /** throw exception if buffer is empty. */
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        if (isEmpty()) {
            return null;
        }
        if (first == capacity) {
            first = 0;
        }
        return rb[first];
    }

    public Iterator<T> iterator() {
        return new BufferIterator();
    }


    private class BufferIterator implements Iterator<T> {

        int wp;

        public BufferIterator() {
            wp = 0;
        }

        @Override
        public boolean hasNext() {
            return wp != fillCount;
        }

        @Override
        public T next() {
            int index = wp + first;
            if (index == capacity) {
                index = 0;
            }
            T returnItem = rb[index];
            wp += 1;
            return returnItem;
        }
    }
}
