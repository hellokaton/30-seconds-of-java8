package xyz.ivan.snippet.array;

import xyz.ivan.snippet.performance.MultiTimesStrategy;
import xyz.ivan.snippet.performance.PerformanceStrategy;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ChunkMain {

    private static PerformanceStrategy strategy = new MultiTimesStrategy(10);

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int size = 2;
        long nanoTime = strategy.execute(() -> {
            chunk1(arr, size);
        });
        System.out.println(nanoTime);
        nanoTime = strategy.execute(() ->{
            chunk(arr, size);
        });
        System.out.println(nanoTime);
    }


    // 将数组切割成特定大小的数组
    private static int[][] chunk(int[] arr, int size){
        return IntStream.iterate(0, i -> i + size)
                .limit((long) Math.ceil((double) arr.length / size))
                .mapToObj(cur -> Arrays.copyOfRange(arr, cur, cur + size > arr.length ? arr.length : cur + size))
                .toArray(int[][]::new);
    }


    // 没有使用流的高性能的做法
    private static int[][] chunk1(int[] arr, int size) {
        int numChunks = (int) Math.ceil((double) arr.length / size);
        int[][] result = new int[numChunks][];

        for (int i = 0; i < numChunks; i++) {
            int start = i * size;
            int length = Math.min(size, arr.length - start);
            result[i] = new int[length];
            System.arraycopy(arr, start, result[i], 0, length);
        }

        return result;
    }

}
