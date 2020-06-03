package com.urise.webapp;

public class TestSingleton {

    private static TestSingleton instance;
    private static TestSingleton getInstance() {
        if (instance == null)
            instance = new TestSingleton();
        return instance;
    }
    private TestSingleton() {

    }

    public static void main(String[] args) {
        for (Singleton value : Singleton.values())
            System.out.println(value);
        System.out.println(Singleton.valueOf("INSTANCE").ordinal());
    }

    public enum Singleton {
        SINGLETON, INSTANCE
    }
}
