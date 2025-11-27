package org.example.multithreaded_counter.impl;

import org.example.multithreaded_counter.SiteVisitCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        counter.incrementAndGet();
    }

    @Override
    public int getVisitCount() {
        return counter.get();
    }
}
