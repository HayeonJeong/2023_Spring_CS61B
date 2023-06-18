import deque.MaxArrayDeque;
import deque.ArrayDeque;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    // create multiple Comparator<T> classes to test your code.
    // get practice using Comparator objects to do sth useful
    public static class IntComparator implements Comparator<Integer> {
        public IntComparator() {
        }

        @Override
        public int compare(Integer int1, Integer int2) {
            return int1.compareTo(int2);
        }


        @Test
        public void testMaxArrayDeque() {
            Comparator<Integer> compare = new IntComparator();
            //assertTrue(maxComp.isEmpty());

            // 3.1) Test add and get(0)
            MaxArrayDeque<Integer> maxComp1 = new MaxArrayDeque<>(compare);
            for (int i = 0; i < 5; i++) {
                maxComp1.addLast(i);
            }
            assertEquals(0, (int) maxComp1.get(0));

            // 3.4)
            MaxArrayDeque<Integer> maxComp2 = new MaxArrayDeque<>(compare);
            maxComp2.addFirst(4);
            maxComp2.addFirst(3);
            maxComp2.addFirst(7);
            maxComp2.addFirst(6);
            assertEquals(7, (int) maxComp2.max());
            maxComp2.removeFirst();
            maxComp2.removeFirst();
            assertEquals(4, (int) maxComp2.max());
            maxComp2.addFirst(10);
            maxComp2.addFirst(9);
            assertEquals(10, (int) maxComp2.max());

            // 3.2)
            MaxArrayDeque<Integer> maxComp3 = new MaxArrayDeque<>(compare);
            assertTrue(maxComp3.isEmpty());
            maxComp3.addFirst(4);
            maxComp3.addFirst(3);
            maxComp3.addLast(7);
            maxComp3.addLast(6);
            assertEquals(4, maxComp3.size());
            assertTrue(!maxComp3.isEmpty());

            // 3.4)
            MaxArrayDeque<Integer> maxComp4 = new MaxArrayDeque<>(compare);
            maxComp4.addFirst(4);
            maxComp4.addFirst(3);
            maxComp4.addLast(7);
            maxComp4.addLast(6);
            assertEquals(7, (int) maxComp4.max());
            maxComp4.removeFirst();
            maxComp4.removeLast();
            maxComp4.removeFirst();
            maxComp4.removeLast();
            assertTrue(maxComp4.isEmpty());
            maxComp4.addFirst(4);
            maxComp4.addFirst(3);
            maxComp4.addLast(7);
            maxComp4.addLast(6);
            assertEquals(7, (int) maxComp4.max());
            assertEquals(4, maxComp3.size());

            //assertEquals(10, (int) maxComp.max(compare.reversed()));
        }
    }

    public static class DoubleComparator implements Comparator<Double> {
        public DoubleComparator() {
        }

        public int compare(Double double1, Double double2) {
            return double1.compareTo(double2);
        }


        @Test
        public void testMaxArrayDeque() {
            Comparator<Double> compare = new DoubleComparator();
            // 3.6)
            MaxArrayDeque<Double> maxComp = new MaxArrayDeque<>(compare);
            maxComp.addFirst(0.0); //[0.0]
            maxComp.addFirst(1.0); //[1.0, 0.0]
            maxComp.addLast(2.0); //[1.0, 0.0, 2.0]
            maxComp.addLast(3.0); //[1.0, 0.0, 2.0, 3.0]
            assertEquals(3.0, (double) maxComp.max());
            assertEquals(4, maxComp.size());

        }
    }
}