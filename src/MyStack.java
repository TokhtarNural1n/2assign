public class MyStack<T> extends MyArrayList<T>{
    private int size;
    public T push(T newItem){
        addLast(newItem);

        return newItem;
    }
    public T peek(){

        return get(size - 1);
    }
    public T pop(){
        T removingItem = peek();
        removeLast();
        return removingItem;
    }
    public boolean empty(){
        return empty();
    }
    public int getSize(){
        return getSize();

    }
}
