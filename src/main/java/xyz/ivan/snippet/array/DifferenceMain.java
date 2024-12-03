package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 从 b 中创建一个集合，然后在 a 上使用 Arrays.stream().filter() 只保留 b 中不包含的值。
 */
public class DifferenceMain {
    public static void main(String[] args) {
        int[] a = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toArray();
        int[] b = IntStream.of( 2,  4,  6,  8,  10).toArray();
        Arrays.stream(difference(a, b)).forEach(System.out::println);
    }

    public static int[] difference(int[] first, int[] second){
        Set<Integer> set = Arrays.stream(second)
                .boxed() // 将基本类型 int 转换为对象类型 Integer，因为集合 Set 需要对象类型。
                .collect(Collectors.toSet());
        return Arrays.stream(first)
                .filter(v -> !set.contains(v))
                .toArray();
    }
}
