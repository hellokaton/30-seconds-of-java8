package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 求两个集合的交集
public class IntersectionMain {
    public static void main(String[] args) {
        int[] first = IntStream.of(1, 2, 3, 4, 5).toArray();
        int[] second = IntStream.of(1, 3, 5,7,9).toArray();
        Arrays.stream(intersection(first,second)).forEach(System.out::println);
    }

    private static int[] intersection(int[] first, int[] second){
        Set<Integer> set = Arrays.stream(first).boxed().collect(Collectors.toSet());
        return Arrays.stream(second)
                .filter(set::contains)
                .toArray();
    }
}
