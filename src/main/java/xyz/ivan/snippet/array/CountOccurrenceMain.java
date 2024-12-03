package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 计算数组中的数出现的次数
 */
public class CountOccurrenceMain {
    public static void main(String[] args) {
        Integer[] arr = Stream.of(1, 1, 2, 3, 3).toArray(i -> new Integer[i]);
        long l = countOccurrence(arr, 4);
        System.out.println(l);
    }

    private static long countOccurrence(Integer[] arr, Integer value){
        return Arrays.stream(arr)
                .parallel() // 通过并行不一定能够提升性能
                .filter(num -> num == value)
                .count();
    }
}
