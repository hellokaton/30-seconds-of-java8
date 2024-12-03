package xyz.ivan.snippet.array;

import java.util.Arrays;

// 初始化一个数组，该数组包含在指定范围内的数字，传入 start 和 end。
public class InitialArrayWithRangeMain {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1,2,3};
        Arrays.stream(initialArrayWithRange(array,0,1)).forEach(System.out::println);
    }
    private static <T> T[] initialArrayWithRange(T[] elements, int begin, int end){
        return Arrays.copyOfRange(elements,begin,end+1);
    }
}
