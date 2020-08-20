package com.urise.webapp.storage.concurrency;

public class Deadlock {
    private static final String LOCK1 = "LOCK1";
    private static final String LOCK2 = "LOCK2";

    public static void main(String[] args) {
        deadLock(LOCK1, LOCK2);
        deadLock(LOCK2, LOCK1);
    }

    private static void deadLock(Object LOCK1, Object LOCK2) {
        new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " waiting for LOCK1");
            synchronized (LOCK1) {
                System.out.println("Monitor " + LOCK1 + " caught by thread " + Thread.currentThread().getName());
                delay(100);
                System.out.println("Thread " + Thread.currentThread().getName() + " waiting for LOCK2");
                synchronized (LOCK2) {
                    System.out.println("Monitor " + LOCK2 + " caught by thread " + Thread.currentThread().getName());
                    delay(10);
                }
            }
        }).start();
    }

    private static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
