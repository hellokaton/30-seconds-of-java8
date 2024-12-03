package xyz.ivan.snippet.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public class InitialArrayWithValuesMain {
    public static void main(String[] args) {
        Arrays.stream(initializeArrayWithValues(5, 2)).forEach(System.out::println);
    }

    private static int[] initializeArrayWithValues(int n, int value){
        return IntStream.generate(()->value).limit(n).toArray();
    }
}
