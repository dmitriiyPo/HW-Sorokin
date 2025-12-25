package org.example.multithreading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {

    private final ExecutorService executor;
    private final Map<String, Integer> results = new HashMap<>();
    private final AtomicInteger taskCounter = new AtomicInteger(1);
    private final AtomicInteger activeTaskCounter = new AtomicInteger(0);


    public DataProcessor(ExecutorService executor) {
        this.executor = executor;
    }


    public String calculateSumTask(List<Integer> numberList) {

       String taskName = "task " + taskCounter.incrementAndGet();

       CalculateSumTask task = new CalculateSumTask(numberList, taskName);

        activeTaskCounter.incrementAndGet();

        CompletableFuture<Integer> taskFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return task.call();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }, executor);


        taskFuture.exceptionally(ex -> {
            System.out.println("Ошибка в задаче " + taskName + ": " + ex.getMessage());
            return null;

        }).thenAccept(result -> {
            synchronized (results) {
                if (result != null) {
                    results.put(taskName, result);
                }
            }

        }).whenComplete((result, ex) -> {
            activeTaskCounter.decrementAndGet();
        });

        return taskName;
    }


    public int getActiveTasksCount() {
        return activeTaskCounter.get();
    }


    public Optional<Integer> getTaskResultByName(String taskName) {
        synchronized (results) {
            return Optional.ofNullable(results.get(taskName));
        }
    }


    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
