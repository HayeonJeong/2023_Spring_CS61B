import deque.ArrayDeque;
import deque.Deque;
import deque.LinkedListDeque;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeTest {
    @Test
    public void firstTest() {
        Deque<String> adq = new ArrayDeque<>();

        adq.addLast("front");
        adq.addLast("middle");
        adq.addLast("back");
        for (String s : adq) {
            System.out.println(s);
        }
    }
    @Test
    public void addLastTestBasicWithoutToList() {
        Deque<String> adq = new ArrayDeque<>();

        adq.addLast("front"); // after this call we expect: ["front"]
        adq.addLast("middle"); // after this call we expect: ["front", "middle"]
        adq.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        assertThat(adq).containsExactly("front", "middle", "back");
    }

    @Test
    public void testEqualDeques() {
        Deque<String> adq1 = new ArrayDeque<>();
        Deque<String> adq2 = new ArrayDeque<>();

        adq1.addLast("front");
        adq1.addLast("middle");
        adq1.addLast("back");

        adq2.addLast("front");
        adq2.addLast("middle");
        adq2.addLast("back");

        assertThat(adq1).isEqualTo(adq2);
    }

    @Test
    public void toStringTest() {
        Deque<String> adq = new ArrayDeque<>();

        adq.addLast("front"); // after this call we expect: ["front"]
        adq.addLast("middle"); // after this call we expect: ["front", "middle"]
        adq.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        System.out.println(adq);
    }

    @Test
    public void ArrayDequeEqualsLinkedListDeque() {
        Deque<String> lld1 = new LinkedListDeque<>();
        Deque<String> adq = new ArrayDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        adq.addLast("front");
        adq.addLast("middle");
        adq.addLast("back");

        //assertThat(lld1).containsExactlyElementsIn(adq).inOrder();
        assertThat(adq).isEqualTo(lld1);
    }
}
