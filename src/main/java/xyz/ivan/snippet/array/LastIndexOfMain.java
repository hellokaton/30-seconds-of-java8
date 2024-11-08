package xyz.ivan.snippet.array;

import java.util.stream.IntStream;

public class LastIndexOfMain {
    public static void main(String[] args) {
        int[] elements = IntStream.of(1, 2, 3, 4, 6, 3).toArray();
        System.out.println(lastIndexOf(elements, 3));
    }

    private static int lastIndexOf(int[] elements, int element){
        // 第一种实现方式
        // return IntStream.range(0, elements.length)
        //         .map(i -> elements.length - 1 - i)
        //         .filter(i -> elements[i] == element)
        //         .findFirst()
        //         .orElse(-1);
        // 第二种实现方式
        return IntStream.iterate(elements.length - 1, i -> i - 1)
                .limit(elements.length)
                .filter(i -> elements[i] == element)
                .findFirst()
                .orElse(-1);
    }
}
