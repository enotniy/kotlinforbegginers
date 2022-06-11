package ru.ekitselyuk.lib.patterns.singleton;

public class SingletonJavaDoubleLockCheck {

    private SingletonJavaDoubleLockCheck() {
    }

    private static volatile SingletonJavaDoubleLockCheck instance;

    public static synchronized SingletonJavaDoubleLockCheck getInstance() {
        SingletonJavaDoubleLockCheck localInstance = instance;
        if (localInstance == null) {
            synchronized (Singleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = new SingletonJavaDoubleLockCheck();
                    instance = localInstance;
                }
            }
        }
        return localInstance;
    }
}
