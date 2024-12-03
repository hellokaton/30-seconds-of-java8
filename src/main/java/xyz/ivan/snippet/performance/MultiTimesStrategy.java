package xyz.ivan.snippet.performance;

public class MultiTimesStrategy implements PerformanceStrategy{
    private int times;

    public MultiTimesStrategy(int times) {
        this.times = times;
    }

    @Override
    public long execute(Runnable runnable) {
        long startTime = System.nanoTime();
        for (int i = 0; i < times; i++) {
            runnable.run();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
