package xyz.ivan.snippet.array;

import java.util.Arrays;

public class WordLengthExtractor {
    public static int[] extractWordLengths(String[] sentences) {
        // 实现代码
        return Arrays.stream(sentences)
                .flatMap(str -> Arrays.stream(str.split(" ")))
                .mapToInt(String::length)
                .toArray();
    }

    public static void main(String[] args) {
        String[] sentences = {
                "Hello world",
                "Java programming",
                "FlatMap is powerful"
        };
        int[] lengths = extractWordLengths(sentences);
        System.out.println(Arrays.toString(lengths));
    }
}

