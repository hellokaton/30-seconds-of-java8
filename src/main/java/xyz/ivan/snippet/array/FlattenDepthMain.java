package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * flatMap中的返回值只能返回stream
 * 同理，flatMapToInt中的返回值只能返回IntStream
 */
public class FlattenDepthMain {
    public static void main(String[] args) {
        int[][][] array = new int[][][]{
                { {1}, {2}, {3} },
                { {4}, {5}, {6} },
        };
        Arrays.stream(flattenDepth(array, 0)).forEach(System.out::println);
    }

    private static Object[] flattenDepth(Object[] elements, int depth){
        if (depth == 0) {
            return elements;
        }
        return Arrays.stream(elements).
                flatMap(el ->
                    el instanceof Object[] ? Arrays.stream(flattenDepth(elements, depth - 1))
                            : Stream.of(el)
                ).toArray();
    }
}
