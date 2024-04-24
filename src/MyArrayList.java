import Interfaces.MyListInterface;

public class MyArrayList<T> implements MyListInterface<T> {
    private Object[] arr;
    private int size;

    public MyArrayList() {
        arr = new Object[5];
        size = 0;
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        arr[index] = element;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) arr[index];
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (size == arr.length) {
            increaseBuffer();
        }

        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = data;
        size++;
    }

    public void add(T data) {
        if (size == arr.length) {
            increaseBuffer();
        }
        arr[size++] = data;
    }

    private void increaseBuffer() {
        Object[] newArr = new Object[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }
    public void addFirst(T item) {
        add(0, item);
    }
    public void addLast(T item) {
        add(size, item);
    }
    public T getFirst() {
        return get(0);
    }
    public T getLast() {
        return get(size - 1);
    }
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[--size] = null;
    }
    public void removeFirst() {
        remove(0);
    }


    public void removeLast() {
        remove(size - 1);
    }

    public void sort() {

    }

    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exist(Object object) {
        return false;
    }

    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(arr, 0, array, 0, size);
        return array;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = arr.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = Math.max(oldCapacity * 2, minCapacity);
            Object[] newArray = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = arr[i];
            }
            arr = newArray;
        }
    }
}
