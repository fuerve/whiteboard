package com.fuerve.whiteboard.milestone3;

/**
 * Home-rolled LinkedHashMap implementation with LRU support - copied from
 * my milestone 1 implementation and augmented to support access ordering.
 */
public class LinkedHashMap<K, V> {
    private static final int TABLE_SIZE = 16;
    
    private final boolean accessOrdered;
    private Entry<K, V> header;
    
    @SuppressWarnings("rawtypes")
    private Entry[] store;
    private int size;
    
    /**
     * Default ctor.
     */
    public LinkedHashMap() {
        this(false);
    }
    
    /**
     * Ctor that allows the specification of access ordering.
     * @param aaccessOrdered Whether this map should be access-ordered.
     */
    public LinkedHashMap(final boolean aaccessOrdered) {
        store = new Entry[TABLE_SIZE];
        header = new Entry<K, V>(null, null);
        header.insertedBefore = null;
        accessOrdered = aaccessOrdered;
    }
    
    /**
     * Gets an item in the map.
     * @param key The key of the entry.
     * @return The value of the entry.
     */
    public V get(final K key) {
        final Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        } else {
            promote(entry, false);
            return entry.getValue();
        }
    }
    
    /**
     * Gets the number of entries in this map.
     * @return The number of entries in this map.
     */
    public int size() {
        return size;
    }
    
    /**
     * Gets a complete entry reference from the map.
     * @param key The key of the entry.
     * @return A reference to the stored entry.
     */
    private Entry<K, V> getEntry(final K key) {
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
            return entry;
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
        
        final boolean isNew = !containsKey(key);
        
        Entry<K, V> newEntry = null;
        if (isNew) {
            newEntry = new Entry(key, value);
        } else {
            newEntry = getEntry(key);
            newEntry.value = value;
        }
        if (store[index] == null) {
            store[index] = newEntry;
        } else {
            Entry<K, V> entry = store[index];
            while (entry.next != null) {
                entry = entry.next;
            }
            entry.next = newEntry;
        }
        
        if (accessOrdered) {
            promote(newEntry, isNew);
        } else {
            Entry<K, V> insertionOrderEntry = header;
            while (insertionOrderEntry.insertedBefore != null) {
                insertionOrderEntry = insertionOrderEntry.insertedBefore;
            }
            insertionOrderEntry.insertedBefore = newEntry;
        }
        
        size++;
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
    
    public V remove(final K key) {
        if (key == null) {
            return null; // TODO: Support the null key entry.
        }
        final int hash = key.hashCode();
        final int index = indexFor(hash);
        
        @SuppressWarnings("unchecked")
        Entry<K, V> entry = (Entry<K, V>) store[index];
        Entry<K, V> previous = null;
        
        while (entry != null && (entry.getKey() != key && !entry.getKey().equals(key))) {
            previous = entry;
            entry = entry.next;
        }
        
        if (previous != null) {
            previous.next = entry.next;
        } else {
            store[index] = entry.next;
        }
        
        // Find in the insertion order list.
        Entry<K, V> insertionCurrent = header.insertedBefore;
        Entry<K, V> insertionPrevious = header;
        
        while (insertionCurrent != null && insertionCurrent != entry) {
            insertionPrevious = insertionCurrent;
            insertionCurrent = insertionCurrent.insertedBefore;
        }
        
        if (insertionPrevious != null) {
            insertionPrevious.insertedBefore = insertionCurrent.insertedBefore;
        } else {
            header.insertedBefore = insertionCurrent.insertedBefore;
        }
        
        size--;
        
        return entry.value;
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
     * Promotes an entry to the head of the access order.
     * @param entry The entry to promote.
     * @param isNew Whether the entry existed in this structure prior to now.
     */
    private void promote(final Entry<K, V> entry, final boolean isNew) {
        if (header.insertedBefore == entry) {
            return;
        }
        if (isNew) {
            entry.insertedBefore = header.insertedBefore;
            header.insertedBefore = entry;
        } else {
            Entry<K, V> insertionOrderEntry = header.insertedBefore;
            Entry<K, V> previousEntry = header;
            while (insertionOrderEntry != null && insertionOrderEntry != entry) {
                previousEntry = insertionOrderEntry;
                insertionOrderEntry = insertionOrderEntry.insertedBefore;
            }
            
            if (previousEntry != header) {
                previousEntry.insertedBefore = insertionOrderEntry.insertedBefore;
                entry.insertedBefore = header.insertedBefore;
                header.insertedBefore = entry;
            }
        }
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
            if (current.insertedBefore == null) {
                throw new IndexOutOfBoundsException();
            }
            current = current.insertedBefore;
            return current;
        }
    }
}
