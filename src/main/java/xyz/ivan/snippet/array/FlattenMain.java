package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 使得数组扁平
 */
public class FlattenMain {
    public static void main(String[] args) {
        Object[] array = new Object[]{ new int[]{1, 2, 3}, new int[]{4, 5, 6} , 7, 8, 9};
        Arrays.stream(flatten(array)).forEach(System.out::println);
    }

    private static int[] flatten(Object [] array){
        return Arrays.stream(array)
                        .flatMapToInt(el -> el instanceof int[] ? Arrays.stream((int [])el) : IntStream.of((int) el))
                        .toArray();

    }

}
