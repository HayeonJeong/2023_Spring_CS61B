package deque;

import java.util.*;

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

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node curr;
        private Deque<T> deque;
        public LinkedListDequeIterator() {
            curr = sentinel.next;
        }
        public boolean hasNext() {
            if (curr != sentinel) {
                return true;
            }
            return false;
        }
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T returnItem = curr.item;
            curr = curr.next;
            return returnItem;
        }
    }

    @Override
    public void addFirst(T x) {
        Node N;
        Node secondN;
        if (size == 0) {   //if Empty
            N = new Node(sentinel, x, sentinel);
            //sentinel.next.next.prev = sentinel.next; //맨앞에 추가하기 때문
            sentinel.next.next.prev = N;
            sentinel.next = N;
        } else {
            Node pastFirst = sentinel.next;
            secondN = new Node(sentinel, x, pastFirst);
            pastFirst.prev = secondN;
            sentinel.next = secondN;
        }
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node N;
        Node secondN;
        if (size == 0) {
            N = new Node(sentinel, x, sentinel);
            sentinel.prev.prev.next = N;
            sentinel.prev = N;
        } else {
            Node pastLast = sentinel.prev;
            secondN = new Node(pastLast, x, sentinel);
            pastLast.next = secondN;
            sentinel.prev = secondN;
        }
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<T>();
        Node currNode = sentinel.next;
        //T item = sentinel.next.item;
        int count = 0;
        while (currNode.item != null) {
            returnList.add(currNode.item);
            count++;
            if (size == count) {
                break;
            }
            currNode = currNode.next;
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
        if (!isEmpty()) {
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
        if (!isEmpty()) {
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


    public T getRecursiveRe(int index, int currIndex, Node currNode) {

        if (currIndex == index) {
            return currNode.item;
        }
        return getRecursiveRe(index, currIndex + 1, currNode.next);
    }

    @Override
    public T getRecursive(int index) {
        if (!isEmpty()) {
            if (0 <= index && index < size) {
                // 초기화
                int currIndex = 0;
                Node currNode = sentinel.next;
                return getRecursiveRe(index, currIndex, currNode);
            }
        }
        return null;
    }


    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean contains(T x) {
        for (T item: this) {
            if (item.equals(x)) {
                return true;
            }
        }
        return false;
    }

    //I asked something and got help chatGPT to write this code below.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Deque)) {
            return false;
        }
        Deque<?> other = (Deque<?>) obj;
        if (this.size() != other.size()) {
            return false;
        }
        Iterator<?> thisIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();
        while (thisIterator.hasNext()) {
            Object thisElement = thisIterator.next();
            Object otherElement = otherIterator.next();
            if (thisElement == null ? otherElement != null : !thisElement.equals(otherElement)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        //return getClass().getName() + "@" + Integer.toHexString(hashCode());
        String returnString = "[";
        for (int i = 0; i < size; i += 1) {
            returnString += get(i);
            if (i + 1 != size) {
                returnString += ", ";
            }
        }
        returnString += "]";
        return returnString;

    }
}
