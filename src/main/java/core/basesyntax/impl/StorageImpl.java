package core.basesyntax.impl;

import core.basesyntax.Storage;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static final int MAX_SIZE = 10;
    private K [] keys;
    private V [] values;
    private int size;

    @SuppressWarnings("unchecked")
    public StorageImpl() {
        keys = (K[]) new Object[MAX_SIZE];
        values = (V[]) new Object[MAX_SIZE];
        size = 0;
    }

    private boolean keysEqual(K k1, K k2) {
        if (k1 == null && k2 == null) {
            return true;
        }
        if (k1 != null) {
            return k1.equals(k2);
        }
        return false;
    }

    @Override
    public void put(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (keysEqual(keys[i], key)) {
                values[i] = value;
                return;
            }
        }
        if (size == MAX_SIZE) {
            throw new IllegalStateException("Storage is full");
        }
        keys[size] = key;
        values[size] = value;
        size++;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (keysEqual(keys[i], key)) {
                return values [i];
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
