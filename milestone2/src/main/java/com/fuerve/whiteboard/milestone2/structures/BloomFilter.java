package com.fuerve.whiteboard.milestone2.structures;

import java.util.BitSet;

/**
 * Basic bloom filter implementation.
 * The lesson here is to keep your bit array larger than your anticipated set
 * cardinality by a significant ratio, and base the number of hashing functions
 * on that.
 */
public class BloomFilter<T> {
    private static final int DEFAULT_RATIO = 32;
    private static final int DEFAULT_CARDINALITY = 256;
    
    // I've never used a BitSet before.  Let's see if the JRE has it.
    private final BitSet bitArray;
    private final int numHashes;
    
    /**
     * Initializes a new bloom filter with a default size for the underlying bit vector.
     */
    public BloomFilter() {
        this(DEFAULT_CARDINALITY);
    }
    
    /**
     * Initializes a new bloom filter with a predetermined size for the underlying bit vector.
     * @param size The projected cardinality of the set.
     */
    public BloomFilter(final int size) {
        // Need to determine how many hashing functions to use.
        // According to this page: http://billmill.org/bloomfilter-tutorial/
        // the ideal number involves the number of expected elements (n)
        // and the size of the bit array (m), such that k = (m/n) * ln(2).
        // Here's another nice reference on the formula for k: http://pages.cs.wisc.edu/~cao/papers/summary-cache/node8.html
        // The ratio of bits to entries still needs to be greater than the cardinality
        // of the set n.
        final int n = size;
        final int m = DEFAULT_RATIO * size;
        bitArray = new BitSet(m);
        final int k = (int)(((double)m / (double)n) * Math.log(2.0));
        if (k < 1) {
            numHashes = 1;
        } else {
            numHashes = k;
        }
    }
    
    /**
     * Adds a key to the filter.
     * @param key The key to add to the filter.
     */
    public void add(final T key) {
        final int[] buckets = hashBuckets(key);
        for (final int bucket : buckets) {
            bitArray.set(bucket);
        }
    }
    
    /**
     * Checks a key for membership in the filter.
     * @param key The key to check.
     * @return True if the key probably exists, false if it definitely does not exist.
     */
    public boolean check(final T key){
        boolean result = true;
        final int[] buckets = hashBuckets(key);
        for (final int bucket : buckets) {
            result &= bitArray.get(bucket);
        }
        return result;
    }
    
    /**
     * Line-for-line port of the 64-bit version of this C++ implementation 
     * of the murmur hashing function: https://sites.google.com/site/murmurhash/
     * @param key The key to hash.
     * @return The murmur hash output.
     */
    private int computeHash(final T key, final int seed) {
        // Magic numbers are why I kind of hate hashing functions.
        final int m = 0x5bd1e995;
        final int r = 24;
        int h = seed ^ 4;  // Ah, I see, that's the length in bytes.
        int k = key.hashCode(); // This is what I need to start with.
        k *= m;
        k ^= k >> r;
        k *= m;
        
        h *= m;
        h ^= k;
        
        h ^= h >> 13;
        h *= m;
        h ^= h >> 15;

        return h;
    }
    
    /**
     * Borrowing extremely heavily from the Cassandra method (https://github.com/facebookarchive/cassandra/blob/master/src/org/apache/cassandra/utils/Filter.java),
     * which in turn uses the incremental hashing method found in this paper: https://www.eecs.harvard.edu/~michaelm/postscripts/tr-02-05.pdf.
     * @param key The key to hash.
     * @return The resultant bucket of each hashing function.
     */
    private int[] hashBuckets(final T key) {
        final int[] result = new int[numHashes];
        final int hash1 = computeHash(key, 37);
        final int hash2 = computeHash(key, hash1);
        for (int i = 0; i < numHashes; i++) {
            result[i] = Math.abs((hash1 + i * hash2) % bitArray.size());
        }
        return result;
    }
}
