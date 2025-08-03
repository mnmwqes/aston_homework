package customcollections;

import java.util.LinkedList;

public class MyHashSet<T> {
    private final int SIZE = 16;
    private LinkedList<T>[] buckets;

    public MyHashSet() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void add(T value) {
        int index = Math.abs(value.hashCode() % SIZE);
        if (!buckets[index].contains(value)) {
            buckets[index].add(value);
        }
    }

    public void remove(T value) {
        int index = Math.abs(value.hashCode() % SIZE);
        buckets[index].remove(value);
    }
}