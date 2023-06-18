package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public int initialCapacity;
    public double loadFactor;
    public int tableSize;
    public int currItems = 0;
    public int currLoadFactor;

    /** Constructors */
    public MyHashMap() {
        this.initialCapacity = INITIAL_CAPACITY;
        this.loadFactor = LOAD_FACTOR;
        buckets = createTable(this.initialCapacity);
    }

    public MyHashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = LOAD_FACTOR;
        buckets = createTable(this.initialCapacity);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        buckets = createTable(this.initialCapacity);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() { return new LinkedList<>(); }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        this.tableSize = tableSize;
        Collection<Node>[] newCollection = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            newCollection[i] = createBucket();
        }
        return newCollection;
    }

    @Override
    public void put(K key, V value) {
        if ((currItems + 1)/(double)tableSize > loadFactor) {
            buckets = resize();
        }
        int mod = Math.floorMod(key.hashCode(), tableSize);

        //if the key exists
        for (Node bucket: buckets[mod]) {
            if (bucket.key.equals(key)) {
                bucket.value = value;
                //if we change the value of the key that existed, we should not add the key after this line
                return;
            }
        }

        Node node = createNode(key, value);
        buckets[mod].add(node);
        currItems++;
        currLoadFactor = currItems / tableSize;
    }

    private Collection<Node>[] resize(){
        int prevTableSize = tableSize;
        tableSize *= 2;
        Collection<Node>[] newBuckets = createTable(tableSize);
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                int mod = Math.floorMod(node.key.hashCode(), tableSize);
                newBuckets[mod].add(node);
            }
        }
        currLoadFactor = currItems / tableSize;
        //loadFactor = loadFactor * prevTableSize / tableSize;
        return newBuckets;
    }

    @Override
    public V get(K key) {
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                if (node.key.equals(key)){
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                if (node.key.equals(key)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return currItems;
    }

    @Override
    public void clear() {
        buckets = createTable(tableSize);
        currItems = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
