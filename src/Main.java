public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> newList = new MyArrayList<>();
        newList.add(4);
        System.out.println(newList.get(0));
        newList.set(0, 5);
        System.out.println(newList.get(0));
    }
}

