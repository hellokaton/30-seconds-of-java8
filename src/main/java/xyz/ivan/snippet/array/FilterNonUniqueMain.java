package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FilterNonUniqueMain {
    public static void main(String[] args) {
        int[] array = IntStream.of(1, 1, 2, 3, 4).toArray();
        Arrays.stream(filterNonUnique(array)).forEach(System.out::println);
    }

    private static int[] filterNonUnique(int[] elements){
        return Arrays.stream(elements)
                .filter(element -> indexOf(elements, element) != lastIndexOf(elements, element))
                .distinct()
                .toArray();
    }

    private static int indexOf(int[] elements, int element){
        return IntStream.range(0, elements.length)
                .filter(i -> elements[i] == element)
                .findFirst()
                .orElse(-1);
    }

    private static int lastIndexOf(int[] elements, int element){
        return IntStream.iterate(elements.length - 1, i -> i - 1)
                .limit(elements.length)
                .filter(i -> elements[i] == element)
                .findFirst()
                .orElse(-1);
    }
}
