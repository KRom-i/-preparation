import java.util.Arrays;

public class MyArrayList<T extends Comparable<T>> implements MyCollection<T> {

    private final int DEFAULT_CAPACITY = 10;
    private final float INCREASE = 1.5f;

    private T[] list;
    private int size;

    public MyArrayList (int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        list = (T[]) new Comparable[capacity];
    }

    public MyArrayList () {
        list = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public void insert(int index, T item) {
        if (!checkSize()) increaseСapacity();

        checkIndex(index);

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = item;
        size++;
    }

    private int index(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i] != null && list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(T item) {
        return index(item);
    }

    public boolean remove(T item) {
        int i = index(item);
        if (i == -1) {
            return false;
        }
        remove(i);
        return true;
    }

    public void remove(int index) {
        checkIndex(index);

        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        checkIndex(index);
        return list[index];
    }

    private boolean checkSize () {
        return size < list.length;
    }

    private void increaseСapacity () {
        int length = (int)(list.length * INCREASE) + 1;
        list = Arrays.copyOf (list, length);
    }

    private void checkIndex (int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index < 0 || index > size");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]).append(", ");
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

}
