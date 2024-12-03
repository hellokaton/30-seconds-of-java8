package xyz.ivan.snippet.array;

import java.util.stream.IntStream;

// 查找数组中元素的索引，在不存在元素的情况下返回-1。
public class IndexOfMain {
    public static void main(String[] args) {
        int[] elements = IntStream.of(1, 2, 3, 4, 6).toArray();
        System.out.println(indexOf(elements, 5));
    }

    private static int indexOf(int[] elements, int element){
        return IntStream.range(0, elements.length)
                .filter(i -> elements[i] == element)
                .findFirst()
                .orElse(-1);
    }
}
