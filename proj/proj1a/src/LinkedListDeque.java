import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque<T> implements Deque<T> {
    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        //lld.addFirst(3);
        //lld.addLast(5);
    }

    public class Node {
        //public Node sentinel;
        private Node prev;
        private Node next;
        private T item;
        //Constructor of 'Node' class
        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
    private Node sentinel;
    private int size;

    //Constructor of 'LinkedListDeque' Node
    public LinkedListDeque() {
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node N;
        Node N_N;
        if (size == 0) {   //if Empty
            N = new Node(sentinel, x, sentinel);
            //sentinel.next.next.prev = sentinel.next; //맨앞에 추가하기 때문
            sentinel.next.next.prev = N;
            sentinel.next = N;
        } else {
            Node past_first = sentinel.next;
            N_N = new Node(sentinel, x, past_first);
            past_first.prev = N_N;
            sentinel.next = N_N;
        }
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node N;
        Node N_N;
        if (size == 0) {   //isEmpty
            N = new Node(sentinel, x, sentinel);
            sentinel.prev.prev.next = N;
            sentinel.prev = N;
        }
        else {
            Node past_last = sentinel.prev;
            N_N = new Node(past_last, x, sentinel);
            past_last.next = N_N;
            sentinel.prev = N_N;
        }
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<T>();
        Node curr_node = sentinel.next;
        //T item = sentinel.next.item;
        int count = 0;
        while (curr_node.item != null) {
            returnList.add(curr_node.item);
            count++;
            if (size == count) {
                break;
            }
            curr_node = curr_node.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty() == false) {
            T element = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;

            return element;
        }

        return null;
    }

    @Override
    public T removeLast() {
        if (isEmpty() == false) {
            T element = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;

            return element;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
        Node currNode = sentinel.next;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.item;
    }


    public T getRecursive_re(int index, int curr_index, Node curr_node) {

        if (curr_index == index) {
            return curr_node.item;
        }
        return getRecursive_re(index, curr_index + 1, curr_node.next);
    }

    @Override
    public T getRecursive(int index) {
        if (isEmpty() == false) {
            if (0 <= index && index < size) {
                // 초기화
                int curr_index = 0;
                Node curr_node = sentinel.next;
                return getRecursive_re(index, curr_index, curr_node);
            }
        }
        return null;
    }
}
