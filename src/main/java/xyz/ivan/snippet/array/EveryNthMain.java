package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.IntStream;

// 返回数组中的每个第n个元素。
// egg: 1,2,3,4,5,6  nth=1 res:1,2,3,4,5,6
// egg: 1,2,3,4,5,6  nth=2 res:2,4,6
// egg: 1,2,3,4,5,6  nth=3 res:3,6

public class EveryNthMain {
    public static void main(String[] args) {
        Arrays.stream(everyNth(IntStream.of(1,2,3,4,5,6).toArray(), 4)).forEach(System.out::println);
    }
    public static int[] everyNth(int[] elements, int nth) {
        return IntStream.range(0, elements.length)
                .filter(i -> (i + 1) % nth == 0)
                .map(i -> elements[i])
                .toArray();
    }
}
