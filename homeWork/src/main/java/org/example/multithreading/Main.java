package org.example.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        System.out.println("\nЗапуск 10 потоков");
        runTest(Executors.newFixedThreadPool(10));

//        System.out.println("Запуск 1 потока");
//        runTest(Executors.newSingleThreadExecutor());
    }


    public static void runTest(ExecutorService executor) {

        DataProcessor dp = new DataProcessor(executor);
        List<String> taskNumes = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            List<Integer> listNumber = new ArrayList<>();
            int size = random.nextInt(10) + 5;

            for (int j = 0; j < size; j++) {
                listNumber.add(random.nextInt(100));
            }

            String taskName = dp.calculateSumTask(listNumber);
            taskNumes.add(taskName);
        }

        System.out.println("\nВсе 100 задач поданы. Ожидание завершения...");


        while(dp.getActiveTasksCount() > 0) {
            System.out.println("\nАктивных задач: " + dp.getActiveTasksCount());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("\nВсе задачи завершены! Активных задач: " + dp.getActiveTasksCount());


        System.out.println("\nРезультат выполнения задач:");
        for (String name : taskNumes) {
            Optional<Integer> result = dp.getTaskResultByName(name);
            System.out.println(name + " -> " + result.orElse(-1));
        }


        dp.shutdown();
        System.out.println("\nExecutor завершён.\n");
    }

}
