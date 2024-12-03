package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

/**
 * 使用IntBinaryOperator实现DifferenceWith，而不是set
 */
public class DifferenceWithMain {
    public static void main(String[] args) {
        int[] first = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toArray();
        int[] second = IntStream.of(2, 4, 6, 8, 10, 12).toArray();
        Arrays.stream(differenceWith(first, second, (a, b) -> a - b)).forEach(System.out::println);
    }

    public static int[] differenceWith(int[] first, int[] second, IntBinaryOperator comparator){
        return Arrays.stream(first)
                .filter(a ->
                    Arrays.stream(second)
                            .noneMatch(b -> comparator.applyAsInt(a, b) == 0)
                ).toArray();
    }
}
