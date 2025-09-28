import java.util.Objects;

public class MyHashMap<K, V> {

    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] table;
    private int size = 0;

    public MyHashMap() {
        table = new Entry[INITIAL_CAPACITY];
    }

    // Внутренний класс для представления записи
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Хеш-функция для вычисления индекса в массиве
    private int hash(K key) {
        return Objects.hashCode(key) % table.length;
    }

    // Метод для добавления элемента в HashMap
    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry<K, V> current = table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            newEntry.next = table[index];
            table[index] = newEntry;
        }

        size++;

        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }
    }

    // Метод для получения значения по ключу
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    // Метод для удаления элемента по ключу
    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        Entry<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;  // Если ключ не найден
    }

    // Метод для увеличения размера массива при необходимости
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    // Метод для получения размера HashMap
    public int size() {
        return size;
    }

    // Метод для проверки пустоты HashMap
    public boolean isEmpty() {
        return size == 0;
    }
}