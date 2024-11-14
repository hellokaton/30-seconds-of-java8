package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 返回数组中除去最后一个的所有元素。
 */
public class InitialMain {
    public static void main(String[] args) {
        Object[] array = IntStream.of(1, 2, 3).boxed().toArray();
        Arrays.stream(initial(array)).forEach(System.out::println);
    }

    private static <T> T[] initial(T[] elements){
        return Arrays.copyOfRange(elements, 0, elements.length - 1);
    }
}
