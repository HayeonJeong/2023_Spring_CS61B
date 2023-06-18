import deque.ArrayDeque;
import deque.Deque;
import deque.LinkedListDeque;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDequeTest {
    @Test
    public void firstTest() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        for (String s : lld1) {
            System.out.println(s);
        }
    }

    @Test
    public void addLastTestBasicWithoutToList() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back");
    }

    @Test
    public void testEqualDeques() {
        Deque<String> lld1 = new LinkedListDeque<>();
        Deque<String> lld2 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void toStringTest() {
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1);
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
        assertThat(lld1).isEqualTo(adq);
    }
}
