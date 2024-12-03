package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 根据给定函数对数组元素进行分组。
 */
public class GroupByMain {
    public static void main(String[] args) {
        String[] array = new String[]{"a","b","c","ab","bc","abc"};
        groupBy(array, element->element.contains("a")).entrySet().forEach(System.out::println);
    }
    private static <T,R> Map<R, List<T>> groupBy(T[] elements, Function<T,R> func){
        return Arrays.stream(elements).collect(Collectors.groupingBy(func));
    }
}
