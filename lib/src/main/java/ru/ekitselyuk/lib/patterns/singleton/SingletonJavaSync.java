package ru.ekitselyuk.lib.patterns.singleton;

public class SingletonJavaSync {

    private SingletonJavaSync() {
    }

    private static SingletonJavaSync instance;

    public static synchronized SingletonJavaSync getInstance() {
        if (instance == null) {
            instance = new SingletonJavaSync();
        }
        return instance;
    }
}
