package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    protected int fillCount;
    protected int capacity;

    /** Return size of the buffer. */
    public int capacity() {
        return capacity;
    }

    /** Return number of items currently in the buffer. */
    public int fillCount() {
        return fillCount;
    }

}
