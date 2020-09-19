package com.daryll.multithreadparallel.runnables;

public class Client {

    public static void someMethod() {

        TaskClass task = new TaskClass();

        Thread thread = new Thread(task);

        thread.start();
    }

    public static void main(String[] args) {
        someMethod();
    }
}
