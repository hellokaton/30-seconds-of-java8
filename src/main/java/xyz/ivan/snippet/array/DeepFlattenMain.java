package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 使用递归实现数组扁平化
 */
public class DeepFlattenMain {
    public static void main(String[] args) {
        Object[][][] objs = new Object[][][]{{{1}, {2}, {3}},{{4}, {5}, {6}}};
        Arrays.stream(deepFlatten(objs)).forEach(System.out::println);
    }

    public static int[] deepFlatten(Object[] input){
        return Arrays.stream(input)
                .flatMapToInt(o -> {
                    if(o instanceof Object[]){
                        return Arrays.stream(deepFlatten((Object[]) o));
                    }
                    return IntStream.of((Integer) o);
                }).toArray();
    }
}
