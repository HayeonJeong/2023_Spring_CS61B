import java.util.List;
import java.util.ArrayList;

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

    public void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        /*
        int newArraySize = 0;

        for (int i = 0; nextFirst + (i + 1) % items.length < items.length; i++) {
            newArray[i] = items[nextFirst + (i + 1) % items.length];
            newArraySize++;
        }
        for (int j = 0; j % items.length <= nextFirst; j++) {
            newArray[newArraySize] = items[j];
            newArraySize++;
        }
        */
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(nextFirst + i + 1) % items.length];
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
}
