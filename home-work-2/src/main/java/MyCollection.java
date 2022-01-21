public interface MyCollection<T> {

    void insert(int index, T item);

    boolean remove(T item);

    int indexOf(T item);

    int size();

    boolean isEmpty();

}
