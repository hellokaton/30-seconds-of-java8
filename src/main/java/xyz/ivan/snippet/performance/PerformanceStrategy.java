package xyz.ivan.snippet.performance;

public interface PerformanceStrategy {
    long execute(Runnable runnable);
}
