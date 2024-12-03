package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DropRightMain {
    public static void main(String[] args) {
        Arrays.stream(dropRight(IntStream.of(1, 2, 3).toArray(), 1)).forEach(System.out::println);
    }

    private static int[] dropRight(int[] elements, int n){
        if(n < 0){
            throw new IllegalArgumentException("n is less than 0");
        }
        return n < elements.length ? Arrays.copyOfRange(elements, 0, elements.length - n)
                : new int[0];
    }
}
