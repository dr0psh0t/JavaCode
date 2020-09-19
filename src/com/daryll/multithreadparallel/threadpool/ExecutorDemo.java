package com.daryll.multithreadparallel.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {

        //  create a fixed thread pool with maximum three threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        //ExecutorService executor = Executors.newFixedThreadPool(1);
        //- the three runnable tasks will be executed sequentially because there is only
        //one thread in the pool

        //ExecutorService executor = Executors.newCachedThreadPool();
        //- new threads will be created for each waiting task, so all the task will be
        //executed concurrently.

        //  submit runnable tasks to the executor
        executor.execute(new PrintChar('a', 10));
        executor.execute(new PrintChar('b', 10));
        executor.execute(new PrintNum(10));

        //  shut down the executor
        executor.shutdown();
    }
}

class PrintChar implements Runnable {
    private char charToPrint;
    private int times;

    public PrintChar(char c, int t) {
        charToPrint = c;
        times = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.print(charToPrint);
        }
    }
}

class PrintNum implements Runnable {
    private int lastNum;

    public PrintNum(int n) {
        lastNum = n;
    }

    @Override
    public void run() {
        for (int i = 1; i <= lastNum; i++) {
            System.out.print(" "+i);
        }
    }
}