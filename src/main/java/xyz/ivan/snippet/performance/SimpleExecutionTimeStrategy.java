package xyz.ivan.snippet.performance;

public class SimpleExecutionTimeStrategy implements PerformanceStrategy{
    @Override
    public long execute(Runnable runnable) {
        long startTime = System.nanoTime();
        runnable.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
