package customcollections;

import java.util.Arrays;

public class MyList<T> {
    private Object[] data;
    private int size;

    public MyList() {
        data = new Object[10];
        size = 0;
    }

    public void add(T element) {
        ensureCapacity();
        data[size++] = element;
    }

    public T get(int index) {
        checkBounds(index);
        return (T) data[index];
    }

    public T remove(int index) {
        checkBounds(index);
        T removed = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return removed;
    }

    public void addAll(MyList<T> other) {
        for (int i = 0; i < other.size(); i++) {
            this.add(other.get(i));
        }
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, size * 2);
        }
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    public int size() {
        return size;
    }
}