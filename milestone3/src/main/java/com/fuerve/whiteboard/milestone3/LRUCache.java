package com.fuerve.whiteboard.milestone3;

import com.fuerve.whiteboard.milestone2.structures.LinkedList;
import com.fuerve.whiteboard.milestone2.structures.List;

/**
 * Simple LRU cache.  Completely in memory.
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 100;
    
    private int capacity;
    
    /**
     * Ctor.
     */
    public LRUCache() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Ctor that allows specification of a capacity.  This capacity
     * will never be exceeded, and the least recently used entries
     * will be pruned as new entries threaten to exceed this capacity.
     * @param ccapacity The capacity of the cache.
     */
    public LRUCache(final int ccapacity) {
        super(true);
        capacity = ccapacity;
    }
    
    @Override
    public void put(final K key, final V value) {
        if (size() + 1 >= capacity) {
            prune(1);
        }
        super.put(key, value);
    }
    
    private void prune(final int number) {
        int count = 1;
        
        final Iterator<K, V> iter = getIterator();
        final List<K> pruneList = new LinkedList<K>(); // Yeah, I'm being cheap here.
        while (iter.hasNext()) {
            final Entry<K, V> next = iter.next();
            if (count > capacity - number) {
                pruneList.add(next.getKey());
            }
            count++;
        }
        
        for (final K key : pruneList) {
            remove(key);
        }
    }
}
