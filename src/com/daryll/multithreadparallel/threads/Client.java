package com.daryll.multithreadparallel.threads;

public class Client {

    public static void main(String[] args) {
        new Client().someMethod();
    }

    public void someMethod() {

        //  create a thread
        CustomThread thread1 = new CustomThread("Daryll");

        //  start a thread
        thread1.start();

        //  create another thread
        CustomThread thread2 = new CustomThread("David");

        //  start a thread
        thread2.start();
    }
}