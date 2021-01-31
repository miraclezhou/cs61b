package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(10);
        arb.enqueue("1");
        arb.enqueue("2");
        arb.enqueue("3");
        arb.enqueue("a");
        arb.enqueue("b");
        arb.enqueue("c");

        Iterator<String> it = arb.iterator();
        arb.dequeue();
        assertEquals(5, arb.fillCount());
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testPeek() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(10);
        arb.enqueue("1");
        arb.enqueue("2");
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
