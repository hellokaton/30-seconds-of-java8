package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 数组去重
 */
public class DistinctValuesOfArrayMain {
    public static void main(String[] args) {
        int[] array = IntStream.of(1, 1, 2, 3, 3, 2).toArray();
        Arrays.stream(distinctValuesOfArray(array)).forEach(System.out::println);
    }

    private static int[] distinctValuesOfArray(int[] arr){
        return Arrays.stream(arr).distinct().toArray();
    }
}
