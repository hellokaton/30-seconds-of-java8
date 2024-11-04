package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class DropElementsMain {
    public static void main(String[] args) {
        int[] elements = IntStream.of(0, 2, 3, 1, 2, 3).toArray();
        Arrays.stream(dropElements(elements, element -> Integer.compare(element, 1) == 0)).forEach(System.out::println);
    }

    private static int[] dropElements(int[] elements, IntPredicate predicate){
        while(elements.length > 0 && !predicate.test(elements[0])){
            elements = Arrays.copyOfRange(elements, 1, elements.length);
        }
        return elements;
    }
}
