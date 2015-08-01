package com.fuerve.whiteboard.hashmap;


/**
 * HashMap implementation.
 * Note that this implementation is about the data structure itself, not the optimality of the hashing function.
 * This is also a very simplistic implementation in terms of structure.  The java.util implementation is really
 * quite a lot better for general-purpose use for a lot of reasons.
 */
public class MyHashMap<K, V> {
    private static final int TABLE_SIZE = 16;
    
    @SuppressWarnings("rawtypes")
    private Entry[] store;
    
    
    public MyHashMap() {
        store = new Entry[TABLE_SIZE];
    }
    
    /**
     * Gets an item in the map.
     * @param key The key of the entry.
     * @return The value of the entry.
     */
    public V get(final K key) {
        if (key == null) {
            return null; // TODO: Support the null key entry.
        }
        final int hash = key.hashCode();
        final int index = indexFor(hash);
        
        @SuppressWarnings("unchecked")
        Entry<K, V> entry = (Entry<K, V>) store[index];
        
        while (entry != null && (entry.getKey() != key && !entry.getKey().equals(key))) {
            entry = entry.getNext();
        }
        
        if (entry != null) {
            return entry.getValue();
        } else {
            return null;
        }
    }
    
    /**
     * Puts a value into the map.
     * @param key The key of the entry.
     * @param value The value of the entry.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void put(final K key, final V value) {
        if (key == null) {
            return; // TODO: Support the null key entry.
        }
        final int hash = key.hashCode();
        final int index = indexFor(hash);
        
        if (store[index] == null) {
            store[index] = new Entry(key, value);
        } else {
            Entry<K, V> entry = store[index];
            while (entry.next != null) {
                entry = entry.next;
            }
            entry.next = new Entry(key, value);
        }
    }
    
    /**
     * Determines whether a key exists in the map.
     * @param key The key for which to check.
     * @return True if the key exists in the map.
     */
    public boolean containsKey(final K key) {
        if (key == null) {
            return false; // TODO: Support the null key entry.
        }
        final int hash = key.hashCode();
        final int index = indexFor(hash);
        
        @SuppressWarnings("unchecked")
        Entry<K, V> entry = (Entry<K, V>) store[index];
        
        while (entry != null && (entry.getKey() != key && !entry.getKey().equals(key))) {
            entry = entry.getNext();
        }
        
        return entry != null;
    }
    
    /**
     * Determines whether a value exists in the map.
     * @param value The value for which to check.
     * @return True if the value exists in the map.
     */
    public boolean containsValue(final V value) {
        for (int i = 0; i < store.length; i++) {
            if (store[i] != null) {
                @SuppressWarnings("unchecked")
                Entry<K, V> entry = (Entry<K, V>) store[i];
                
                while (entry != null) {
                    if (entry.getValue() == value || entry.getValue().equals(value)) {
                        return true;
                    }
                    entry = entry.getNext();
                }
            }
        }
        
        return false;
    }
    
    /**
     * Gets the table index for a given hash code.
     * @param hash The input hash code.
     * @return The table index for the hash code.
     */
    private int indexFor(final int hash) {
        return hash & store.length - 1;
    }
    
    /**
     * Contains a single entry in the map.
     *
     * @param <K> The key type.
     * @param <V> The value type.
     */
    public static class Entry<K, V> {
        private K key;
        private V value;
        
        private Entry<K, V> next;
        
        /**
         * Initializes a new instance of Entry.
         * @param kkey The key of the entry.
         * @param vvalue Its value.
         */
        public Entry(final K kkey, final V vvalue) {
            key = kkey;
            value = vvalue;
        }
        
        /**
         * Gets the key of the entry.
         * @return The key.
         */
        public K getKey() {
            return key;
        }
        
        /**
         * Gets the value of the entry.
         * @return The value.
         */
        public V getValue() {
            return value;
        }
        
        /**
         * Gets the next entry in the list.
         * @return The next entry in the list.
         */
        Entry<K, V> getNext() {
            return next;
        }
    }
}
