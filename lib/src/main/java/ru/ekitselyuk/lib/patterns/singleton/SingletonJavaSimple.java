package ru.ekitselyuk.lib.patterns.singleton;

public class SingletonJavaSimple {

    private SingletonJavaSimple() {
    }

    public static final SingletonJavaSimple INSTANCE = new SingletonJavaSimple();
}
