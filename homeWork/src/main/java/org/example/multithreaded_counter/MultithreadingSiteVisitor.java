package org.example.multithreaded_counter;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingSiteVisitor {

    private final SiteVisitCounter siteVisitCounter;
    private final List<Thread> threads = new ArrayList<>();
    private long stratTime;
    private long endTime;

    public MultithreadingSiteVisitor(SiteVisitCounter siteVisitCounter ) {
        this.siteVisitCounter = siteVisitCounter;
    }


    public void visitMultithread(int numOfThreads) {
        stratTime = System.currentTimeMillis();
        for (int i = 0; i < numOfThreads; i++) {
            Thread thread = new Thread(siteVisitCounter::incrementVisitCount);
            threads.add(thread);
            thread.start();
        }
    }


    public void waitUntilAllVisited() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
        endTime = System.currentTimeMillis();
    }


    public double getTotalTimeOfHandling() {
        return (endTime - stratTime) / 1000.0;
    }

}
