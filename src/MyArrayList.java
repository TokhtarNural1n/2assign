import interfaces.MyListInterface;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;

    // A class constructor that creates an array with default capacity.
    public MyArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    // Constructor of a class with a given array capacity.
    public MyArrayList(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Недопустимая емкость: " + capacity);
        array = (T[]) new Object[capacity];
        size = 0;
    }

    // Returns the size of the list.
    @Override
    public int size() {
        return size;
    }

    // Checks the capacity of the array and expands it if necessary.
    public void checkForCapacity() {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[size * 2 + 1];
            for (int i = 0; i < size; i++) {
                newArray[i] = (T) array[i];
            }
            array = newArray;
        }
    }

    // Adds an element to the end of the list.
    @Override
    public void add(T item) {
        checkForCapacity();
        array[size] = item;
        size++;
    }

    // Adds an element to the beginning of the list.
    @Override
    public void addFirst(T item) {
        checkForCapacity();
        for (int i = size; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = item;
        size++;
    }

    // Adds an element to the end of the list.
    @Override
    public void addLast(T item) {
        checkForCapacity();
        array[size] = item;
        size++;
    }

    // Throws an exception if the index is invalid.
    private void throwException(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Недопустимый индекс: " + index);
    }

    // Checks if the list is empty.
    private void checkEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }
    }

    // Adds an element at the specified index.
    @Override
    public void add(int index, T item) {
        throwException(index);
        checkForCapacity();
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = item;
        size++;
    }

    // Removes the first element of the list.
    @Override
    public void removeFirst() {
        checkEmpty();
        for (int i = 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        array[--size] = null;
    }

    // Removes the last element of the list.
    @Override
    public void removeLast() {
        checkEmpty();
        array[--size] = null;
    }

    // Returns the element by index.
    @Override
    public T get(int index) {
        throwException(index);
        return (T) array[index];
    }

    // Returns the first element of the list.
    @Override
    public T getFirst() {
        checkEmpty();
        return (T) array[0];
    }

    // Returns the last element of the list.
    @Override
    public T getLast() {
        checkEmpty();
        return (T) array[size - 1];
    }

    // Clears the list, making it empty.
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    // Converts a list to an array.
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }

    // Returns an iterator for traversing the elements of the list.
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indx = 0;

            @Override
            public boolean hasNext() {
                return indx < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (T) array[indx++];
            }
        };
    }

    // Replaces the element in the list at the specified index.
    @Override
    public Object set(int index, T item) {
        throwException(index);
        return array[index] = item;
    }

    // Checks whether an object exists in a list.
    @Override
    public boolean exists(Object object) {
        for (int i = 0; i < size; i++) {
            if (array[i] == null && object == null) {
                return true;
            } else if (array[i] != null && array[i].equals(object)) {
                return true;
            }
        }
        return false;
    }

    // Sorts list items.
    @Override
    public void sort() {
        T temp;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (((Comparable<T>) array[j]).compareTo(array[j + 1]) > 0) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Returns the index of the first occurrence of an element in a list.
    @Override
    public int indexOf(Object object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (object.equals(array[i]))
                    return i;
            }
        }
        return -1;
    }

    // Returns the index of the last occurrence of an element in a list.
    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
