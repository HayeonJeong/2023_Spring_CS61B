import edu.princeton.cs.algs4.Stopwatch;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

     @Test
     @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
     void noNonTrivialFields() {
         Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
         List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
     }

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.removeFirst(); // when size==0 removeFirst -> null
         lld1.removeLast();  // when size==0 removeLast -> null

         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         assertThat(lld1.toList()).containsExactly(-1, 0, 1).inOrder();
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();

         lld1.removeFirst();   // [-1, 0, 1, 2]
         assertThat(lld1.toList()).containsExactly(-1, 0, 1, 2).inOrder();
         lld1.removeLast();    // [-1, 0, 1]
         lld1.removeLast();    // [-1, 0]
         lld1.removeLast();    // [-1]
         assertThat(lld1.toList()).containsExactly(-1).inOrder();
         lld1.removeLast();    // []
         lld1.removeLast();    // when size==0 removeLast -> null

         assertThat(lld1.toList()).containsExactly().inOrder();
     }

     // Below, you'll write your own tests for LinkedListDeque.
     @Test
     public void isEmptyTest() {
         Deque<Integer> lld = new LinkedListDeque<>();
         assertThat(lld.isEmpty()).isTrue();
         lld.addLast(3);    // [3]
         assertThat(lld.isEmpty()).isFalse();
     }

     @Test
     public void sizeTest() {
         Deque<Integer> lld = new LinkedListDeque<>();
         assertThat(lld.size()).isEqualTo(0);
         for (int i = 0; i < 10000; i++) {
             lld.addLast(i);
         }
         assertThat(lld.size()).isEqualTo(10000);
     }

    @Test
    public void testSizeZero() {
        Deque<Integer> lld = new LinkedListDeque<>();
        assertThat(lld.size()).isEqualTo(0);
    }

    @Test
    public void testSizeOne() {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(3);    // [3]
        assertThat(lld.size()).isEqualTo(1);
    }

    @Test
    public void testSizeAndIsEmpty() {
        Deque<Integer> lld = new LinkedListDeque<>();
        assertThat(lld.size()).isEqualTo(0);
        assertThat(lld.isEmpty()).isTrue();

        for (int i = 0; i < 10000; i++) {
            lld.addLast(i);
        }
        assertThat(lld.size()).isEqualTo(10000);

        assertThat(lld.isEmpty()).isFalse();
    }

    @Test
    public void testGet() {
        Deque<Integer> lld = new LinkedListDeque<>();
        assertThat(lld.get(0)).isEqualTo(null);
        lld.addLast(1);
        assertThat(lld.get(0)).isEqualTo(1);
        lld.addFirst(2);
        assertThat(lld.get(0)).isEqualTo(2);
        assertThat(lld.get(-1)).isEqualTo(null);
     }

    @Test
    public void testGetRecursive() {
        Deque<Integer> lld = new LinkedListDeque<>();
        assertThat(lld.getRecursive(0)).isEqualTo(null);
        lld.addLast(1);
        assertThat(lld.getRecursive(0)).isEqualTo(1);
        lld.addFirst(2);
        assertThat(lld.getRecursive(0)).isEqualTo(2);
        lld.removeLast();
        assertThat(lld.getRecursive(1)).isEqualTo(null);
        assertThat(lld.getRecursive(0)).isEqualTo(2);
        assertThat(lld.getRecursive(-1)).isEqualTo(null);

    }
     
    @Test
    public void testremoveFirst() {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(1); // [1]
        lld.addLast(2); // [1, 2]
        lld.removeFirst(); // [2]
        lld.removeFirst(); // []
        assertThat(lld.getRecursive(0)).isEqualTo(null);
        lld.removeFirst();
        assertThat(lld.getRecursive(0)).isEqualTo(null);
    }

    @Test
    public void testremoveLast() {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(1); // [1]
        lld.addLast(2); // [1, 2]
        lld.removeLast(); // [1]
        assertThat(lld.getRecursive(0)).isEqualTo(1);
        lld.removeLast(); // []
        assertThat(lld.getRecursive(0)).isEqualTo(null);
        lld.removeLast();
        assertThat(lld.getRecursive(0)).isEqualTo(null);

    }

    @Test
    public void testaddFirstandremoveFirst() {
        //addFirst / removeFirst - edge cases
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(1); // [1]
        lld.addFirst(2); // [2, 1]
        lld.removeFirst(); // [1]
        assertThat(lld.getRecursive(0)).isEqualTo(1);
        lld.removeFirst();  // []
        lld.removeFirst();
        assertThat(lld.getRecursive(0)).isEqualTo(null);
    }

    @Test
    public void testaddFirstandremoveLast() {
        //addFirst / removeLast - edge cases
        Deque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(1); // [1]
        lld1.addFirst(2); // [2, 1]
        assertThat(lld1.getRecursive(0)).isEqualTo(2);
        lld1.removeLast(); // [2]
        assertThat(lld1.getRecursive(0)).isEqualTo(2);
        lld1.removeLast();  // []
        lld1.removeLast();
        assertThat(lld1.getRecursive(0)).isEqualTo(null);

        lld1.addFirst(1);
        lld1.addFirst(2);
        assertThat(lld1.getRecursive(0)).isEqualTo(2);

    }

    @Test
    public void testaddLastandremoveFirst() {
        //addLast / removeFirst - edge cases
        Deque<Integer> lld2 = new LinkedListDeque<>();
        lld2.addLast(1); // [1]
        lld2.addLast(2); // [1, 2]
        lld2.removeFirst(); // [2]
        assertThat(lld2.getRecursive(0)).isEqualTo(2);
        lld2.removeFirst();  // []
        lld2.removeFirst();
        assertThat(lld2.getRecursive(0)).isEqualTo(null);
    }

}