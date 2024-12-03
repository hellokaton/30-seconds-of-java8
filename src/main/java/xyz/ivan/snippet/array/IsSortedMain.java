package xyz.ivan.snippet.array;

public class IsSortedMain {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 3, 4, 1};
        int sorted = isSorted(array);
        System.out.println(sorted);
    }
    public static <T extends Comparable<? super T>> int isSorted(T[] arr) {
        final int direction = arr[0].compareTo(arr[1]) < 0 ? 1 : -1;
        for (int i = 0; i < arr.length; i++) {
            T val = arr[i];
            if (i == arr.length - 1) return direction;
            else if ((val.compareTo(arr[i + 1]) * direction > 0)) return 0;
        }
        return direction;
    }

}
