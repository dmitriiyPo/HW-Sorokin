package org.example.multithreaded_counter.impl;

import org.example.multithreaded_counter.SiteVisitCounter;

public class UnsynchronizedCounter implements SiteVisitCounter {

    private int counter = 0;

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        counter++;
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
