import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    public void addFirstTestBasic() {
        Deque<String> adq = new ArrayDeque<>(); //adq means ArrayDeque

        //assertThat(adq.toList()).isEqualTo(null);

        adq.addLast("a");
        assertThat(adq.toList()).containsExactly("a").inOrder();

        adq.addLast("b");
        assertThat(adq.toList()).containsExactly("a", "b").inOrder();

        adq.addFirst("c");
        assertThat(adq.toList()).containsExactly("c", "a", "b").inOrder();

        adq.addLast("d");
        assertThat(adq.toList()).containsExactly("c", "a", "b", "d").inOrder();

        adq.addLast("e");
        assertThat(adq.toList()).containsExactly("c", "a", "b", "d", "e").inOrder();

        adq.addFirst("f");
        assertThat(adq.toList()).containsExactly( "f", "c", "a", "b", "d", "e").inOrder();

        adq.addLast("g");
        adq.addLast("h");
        assertThat(adq.toList()).containsExactly(
                "f", "c", "a", "b", "d", "e", "g", "h").inOrder();

        adq.addLast("Z");
        assertThat(adq.toList()).containsExactly(
                "f", "c", "a", "b", "d", "e", "g", "h", "Z").inOrder();

        adq.addLast("A");
        assertThat(adq.toList()).containsExactly(
                "f", "c", "a", "b", "d", "e", "g", "h", "Z", "A").inOrder();
        adq.addLast("B");
        adq.addLast("C");
        adq.addLast("D");
        adq.addLast("E");
        adq.addLast("F");
        adq.addLast("G");
        assertThat(adq.toList()).containsExactly(
                "f", "c", "a", "b", "d", "e", "g", "h", "Z", "A", "B", "C", "D", "E", "F", "G").inOrder();

        adq.addLast("H");
        assertThat(adq.toList()).containsExactly(
                "f", "c", "a", "b", "d", "e", "g", "h", "Z", "A", "B", "C", "D", "E", "F", "G", "H").inOrder();
    }

    @Test
    public void isEmptyAndSizeTest() {
        Deque<Integer> adq = new ArrayDeque<>();
        assertThat(adq.isEmpty()).isTrue();
        assertThat(adq.size()).isEqualTo(0);
        adq.addLast(3);
        assertThat(adq.isEmpty()).isFalse();
        assertThat(adq.size()).isEqualTo(1);
    }

    @Test
    public void getTest() {
        Deque<Integer> adq = new ArrayDeque<>();
        assertThat(adq.get(3)).isEqualTo(null);

        adq.addLast(3);
        adq.addFirst(2);
        adq.addLast(4);
        adq.addFirst(1);
        adq.addLast(5);

        assertThat(adq.get(3)).isEqualTo(4);
        assertThat(adq.get(100)).isEqualTo(null);
        assertThat(adq.get(-3)).isEqualTo(null);
    }

    @Test
    public void removeFirstAndLastTest() {
        Deque<Integer> adq = new ArrayDeque<>();
        assertThat(adq.removeFirst()).isEqualTo(null);
        assertThat(adq.removeLast()).isEqualTo(null);
        assertThat(adq.size()).isEqualTo(0);

        adq.addLast(3);
        adq.addFirst(2);
        adq.addLast(4);
        adq.addFirst(1);
        adq.addLast(5);

        adq.removeFirst();
        assertThat(adq.toList()).containsExactly(2, 3, 4, 5).inOrder();

        adq.removeLast();
        assertThat(adq.toList()).containsExactly(2, 3, 4).inOrder();

        adq.removeLast();
        adq.removeLast();
        adq.removeLast();
        assertThat(adq.removeFirst()).isEqualTo(null);
        assertThat(adq.removeLast()).isEqualTo(null);

        adq.addLast(3);
        assertThat(adq.removeFirst()).isEqualTo(3);

        adq.removeLast();
        assertThat(adq.size()).isEqualTo(0);

        adq.addFirst(2);
        assertThat(adq.removeFirst()).isEqualTo(2);

        adq.removeFirst();
        assertThat(adq.size()).isEqualTo(0);

    }


    /*
    @Test
    public void resizingDownTest() {
        Deque<Integer> adq = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            adq.addLast(i);
        }
        assertThat(adq.size()).isEqualTo(20);
        for (int j = 0; j < 19; j++) {
            adq.removeLast();
            if (j == 15) {

            }
        }
        assertThat(adq.toList()).containsExactly(0).inOrder();
        assertThat(adq.toList()).isEqualTo(5000);
    }
    */

    @Test
    public void addFirstTriggerResize() {
        Deque<String> adq = new ArrayDeque<>(); //adq means ArrayDeque

        assertThat(adq.toList()).isEqualTo(null);

        adq.addLast("a");
        assertThat(adq.toList()).containsExactly("a").inOrder();

        adq.addLast("b");
        assertThat(adq.toList()).containsExactly("a", "b").inOrder();

        adq.addFirst("c");
        assertThat(adq.toList()).containsExactly("c", "a", "b").inOrder();

        adq.addLast("d");
        assertThat(adq.toList()).containsExactly("c", "a", "b", "d").inOrder();

        adq.addLast("e");
        assertThat(adq.toList()).containsExactly("c", "a", "b", "d", "e").inOrder();

        adq.addFirst("f");
        assertThat(adq.toList()).containsExactly( "f", "c", "a", "b", "d", "e").inOrder();

        adq.addLast("g");
        adq.addLast("h");
        assertThat(adq.toList()).containsExactly(
                "f", "c", "a", "b", "d", "e", "g", "h").inOrder();

        adq.addFirst("Z");
        assertThat(adq.toList()).containsExactly(
                "Z", "f", "c", "a", "b", "d", "e", "g", "h").inOrder();

        adq.addFirst("A");
        assertThat(adq.toList()).containsExactly(
                "A", "Z", "f", "c", "a", "b", "d", "e", "g", "h").inOrder();
    }



}


