package org.example.multithreaded_counter;

import org.example.multithreaded_counter.impl.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String[] counters = {"UnsynchronizedCounter", "VolatileCounter",
                "SynchronizedBlockCounter", "AtomicIntegerCounter", "ReentrantLockCounter"};


        for (String counterType : counters) {
            System.out.println("Тестирование " + counterType + ":");

            //testForCouner(counterType, 10);

            testForCounter(counterType, 100);

            System.out.println();
        }

    }

    private static void testForCounter(String counterType, int numThreads) throws InterruptedException {
        SiteVisitCounter counter = createNewCounter(counterType);
        MultithreadingSiteVisitor visitor = new MultithreadingSiteVisitor(counter);
        visitor.visitMultithread(numThreads);
        visitor.waitUntilAllVisited();
        double totalTime = visitor.getTotalTimeOfHandling();
        int finalCount = counter.getVisitCount();
        System.out.printf("  Потоков: %d, Время: %.2f сек, Итоговый счетчик: %d%n",
                numThreads, totalTime, finalCount);
    }

    private static SiteVisitCounter createNewCounter(String counterType) {
        return switch (counterType) {
            case "UnsynchronizedCounter" -> new UnsynchronizedCounter();
            case "VolatileCounter" -> new VolatileCounter();
            case "SynchronizedBlockCounter" -> new SynchronizedBlockCounter();
            case "AtomicIntegerCounter" -> new AtomicIntegerCounter();
            case "ReentrantLockCounter" -> new ReentrantLockCounter();
            default -> throw new IllegalArgumentException("Неизвестный тип счетчика: " + counterType);
        };
    }
}
