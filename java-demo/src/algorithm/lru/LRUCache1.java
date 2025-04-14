package algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache1<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache1(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
