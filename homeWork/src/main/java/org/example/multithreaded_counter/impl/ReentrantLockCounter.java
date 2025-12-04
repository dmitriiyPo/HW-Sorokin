package org.example.multithreaded_counter.impl;

import org.example.multithreaded_counter.SiteVisitCounter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {

    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    @Override
    public void incrementVisitCount() {
        lock.lock();
        try {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            counter++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getVisitCount() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }

}
