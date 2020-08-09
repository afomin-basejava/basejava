package com.urise.webapp;

public class Deadlock {
    private static final String LOCK1 = "LOCK1";
    private static final Object LOCK2 = "LOCK2";

    public static void main(String[] args) {
        Deadlock mayBeDeadlock = new Deadlock();
        mayBeDeadlock.deadLock(LOCK1, LOCK2);
        mayBeDeadlock.deadLock(LOCK2, LOCK1);
    }

    private void deadLock(Object LOCK1, Object LOCK2) {
        new Thread(() -> {
            synchronized (LOCK1) {
                System.out.println("Monitor " + LOCK1 + " caught by thread " + Thread.currentThread().getName());
                delay(100);
                synchronized (LOCK2) {
                    System.out.println("Monitor " + LOCK2 + " caught by thread " + Thread.currentThread().getName());
                    delay(10);
                }
            }
        }).start();
    }

    private void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
