import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode root;
    private boolean key;

    public class BSTNode {
        private K key;
        private V value;
        private int size;
        private BSTNode leftSubtree;
        private BSTNode rightSubtree;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
            leftSubtree = null;
            rightSubtree = null;
        }
    }

    /*
    public int compareRoots(BSTMap other) {
        return this.root.item.compareTo(other.root.item);
    }
    */


    @Override
    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");

        root = put(root, key, val);
    }

    public BSTNode put(BSTNode node, K key, V value) {

        if (node == null) {
            return new BSTNode(key, value);
        }

        int compare = key.compareTo(node.key);

        //compare and choose where to put
        if (compare < 0) {
            node.leftSubtree = put(node.leftSubtree, key, value);
        } else if (compare > 0) {
            node.rightSubtree = put(node.rightSubtree, key, value);
        } else {
            node.value = value;
        }

        node.size = 1 + size(node.leftSubtree) + size(node.rightSubtree);

        return node;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(BSTNode node, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.key);

        if (compare < 0) {
            return get(node.leftSubtree, key);
        } else if (compare > 0) {
            return get(node.rightSubtree, key);
        } else {
            return node.value;
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (size() == 0) {
            return false;
        }
        if (key == null) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    @Override
    public void clear() {
        root = null;
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

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.leftSubtree);
        System.out.print(node.key + " ");
        printInOrder(node.rightSubtree);
    }

}
