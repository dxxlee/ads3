import java.util.Objects;
public class MyHashTable<K, V> {
    public static class HashNode<K, V> {
        private final K key;
        private V value;
        public HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int capacity;
    private int size;

    public MyHashTable() {
        this(11); // default capacity
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        this.capacity = capacity;
        chainArray = (HashNode<K, V>[]) new HashNode[capacity];
    }

    private int hash(K key) {
        return Math.abs(Objects.hashCode(key)) % capacity;
    } //objects hashcode is in TestHashTable

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if (chainArray[index] == null) {
            chainArray[index] = newNode;
        } else {
            HashNode<K, V> current = chainArray[index];
            while (current.next != null && !current.key.equals(key)) {
                current = current.next;
            }
            if (current.key.equals(key)) {
                // key already exists, updating the value
                current.value = value;
            } else {
                // key doesnt exist, adding it to the end of the chain
                current.next = newNode;
            }
        }
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // if key not found
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (Objects.equals(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> node : chainArray) {
            while (node != null) {
                if (Objects.equals(node.value, value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null; //   if value not found
    }
}
