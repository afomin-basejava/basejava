package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Thread.currentThread;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 1_00;
    private static int counter;
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        MainConcurrency mayBeDeadlock = new MainConcurrency();
        mayBeDeadlock.deadLock(LOCK1, LOCK2);
        mayBeDeadlock.deadLock(LOCK2, LOCK1);
        System.out.println(currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();
        new Thread(() -> System.out.println(currentThread().getName() + ", " + currentThread().getState())).start();
        Thread.sleep(1000);
        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }

    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
        counter++;
//                wait();
//                readFile
//                ...
//        }
    }

    private void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Monitor " + lock1 + " caught by thread " + currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Monitor " + lock2 + " caught by thread " + currentThread().getName());
                    for (int i = 0; i < THREADS_NUMBER; i++) {
                        for (int j = 0; j < 100; j++) {
                            counter++;
                        }
                        System.out.println(counter);
                    }
                }
            }
        }).start();
    }
}
