package deque;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final Integer LENGTH = 16;

    public ArrayDeque() {   //Constructor
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos = nextFirst;

        public ArrayDequeIterator() { }

        public boolean hasNext() {
            int curr = pos + 1;
            int currMud = curr % items.length;
            if (currMud != 0 && curr < 0) {
                if (items[currMud + items.length] == null) {
                    return false;
                }
            }
            if (items[currMud] == null) {
                return false;
            }
            return true;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            pos++;
            int posMud = pos % items.length;
            if (posMud != 0 && pos < 0) {
                return items[posMud + items.length];
            } else {
                return items[posMud];
            }
        }
    }

    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = x;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(nextFirst + i + 1) % items.length];
            //newArray[i] = get(i);
        }

        items = newArray;
        nextFirst = newArray.length - 1;
        nextLast = size;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int currBox = (nextFirst + 1) % items.length;
        while (returnList.size() != size) {
            if (items[currBox] != null) {
                returnList.add(items[currBox]);
            }
            currBox = (currBox + 1) % items.length;
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
        if (isEmpty()) {
            return null;
        }
        if (size <= items.length * 0.25 && LENGTH < items.length) {
            resize(items.length / 2);
        }

        int pos = (nextFirst + 1) % items.length;   //addLast
        T removedItem = items[pos];
        items[pos] = null;
        nextFirst = pos;
        size--;

        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        //resizing
        if (size <= items.length * 0.25 && LENGTH < items.length) {
            resize(items.length / 2);
        }

        int pos = (nextLast - 1 + items.length) % items.length; //addFirst
        T removedItem = items[pos];
        items[pos] = null;
        nextLast = pos;
        size--;

        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int pos = (nextFirst + 1 + index) % items.length;
        return items[pos];
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    /*
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i] != null && items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }
    */

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Deque)) {
            return false;
        }
        Deque<T> otherArrayDeque = (Deque<T>) other;
        if (this.size() != otherArrayDeque.size()) {
            return false;
        }
        int i = 0;
        for (T element : this) {
            T otherElement = otherArrayDeque.get(i);
            if (!Objects.equals(element, otherElement)) {
                return false;
            }
            i++;
        }
        return true;
    }


    @Override
    public String toString() {
        //return getClass().getName() + "@" + Integer.toHexString(hashCode());
        String returnString = "[";
        int curr = nextFirst + 1;
        while (curr != nextLast) {
            returnString += items[curr];
            if (((curr + 1) % items.length) != nextLast) {
                returnString += ", ";
            }
            curr = (curr + 1) % items.length;
        }
        returnString += "]";
        return returnString;

    }
}
