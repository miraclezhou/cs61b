package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    private static final double DRUM_DECAY = 1.0; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {

        int capa = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capa);
        for (int i = 0; i < capa; i += 1) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        for (int i = 0; i < buffer.capacity(); i += 1) {
            buffer.dequeue();
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {

        double firstItem = buffer.dequeue();
        double secondItem = buffer.peek();
        double newItem = 0.5 * (firstItem + secondItem) * DECAY;

        /** Simulate the sound of drums.*/
        /*
        double r = Math.random() - 0.5;
        if (r > 0) {
            newItem = -newItem;
        }
        */
        buffer.enqueue(newItem);


    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.peek();
    }


}
