package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * toArray的参数是当前流的长度，toArray做的事就是声明要转成的数组的类型
 */
public class ConcatMain {
    public static void main(String[] args) {
        Integer[] first = Stream.of(1, 2, 3).toArray(i -> new Integer[i]);
        Integer[] second = Stream.of(4, 5, 6).toArray(Integer[]::new);
        Stream.of(concat(first, second)).forEach(System.out::println);
    }

    public static <T> T[] concat(T[] first, T[] second){
        return Stream.concat(Stream.of(first), Stream.of(second))
                .toArray(i -> (T[]) Arrays.copyOf(new Object[0], i, first.getClass()));

    }
}
