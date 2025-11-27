package org.example.multithreaded_counter.impl;

import org.example.multithreaded_counter.SiteVisitCounter;

public class SynchronizedBlockCounter implements SiteVisitCounter {

    private int counter = 0;
    private final Object monitor = new Object();

    @Override
    public void incrementVisitCount() {
        synchronized (monitor) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            counter++;
        }
    }

    @Override
    public int getVisitCount() {
        synchronized (monitor) {
            return counter;
        }
    }
}
