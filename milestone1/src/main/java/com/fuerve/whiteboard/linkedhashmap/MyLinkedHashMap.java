package com.fuerve.whiteboard.linkedhashmap;

/**
 * Linked hash map implementation.  Much of this is copied from my hash map implementation.
 * The only difference is that it supports iteration over its keys in the order in which
 * they were entered.  It does this by threading linkage between entries as they are added.
 * This implementation does not include access-ordering.
 */
public class MyLinkedHashMap<K, V> {
    private static final int TABLE_SIZE = 16;
    
    private final Entry<K, V> header;
    
    @SuppressWarnings("rawtypes")
    private Entry[] store;
    
    
    public MyLinkedHashMap() {
        store = new Entry[TABLE_SIZE];
        header = new Entry<K, V>(null, null);
        header.insertedBefore = null;
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
        
        final Entry<K, V> newEntry = new Entry(key, value);
        if (store[index] == null) {
            store[index] = new Entry(key, value);
        } else {
            Entry<K, V> entry = store[index];
            while (entry.next != null) {
                entry = entry.next;
            }
            entry.next = newEntry;
        }
        
        Entry<K, V> insertionOrderEntry = header;
        while (insertionOrderEntry.insertedBefore != null) {
            insertionOrderEntry = insertionOrderEntry.insertedBefore;
        }
        insertionOrderEntry.insertedBefore = newEntry;
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
    
    public Iterator<K, V> getIterator() {
        return new Iterator<K, V>(header);
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
        Entry<K, V> insertedBefore;
        
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
    
    public static class Iterator<K, V> {
        private Entry<K, V> current;
        
        public Iterator(final Entry<K, V> head) {
            current = head;
        }
        
        public boolean hasNext() {
            return current.insertedBefore != null;
        }
        
        public Entry<K, V> next() {
            current = current.insertedBefore;
            return current;
        }
    }
}
