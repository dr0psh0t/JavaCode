package com.daryll.multithreadparallel.threadsynchro;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountWithSync {

    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        //  create and lunch 100 threads
        for (int i = 0; i < 100; i++) {
            executor.execute(new AddAPennyTask());
        }

        executor.shutdown();

        //  wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        System.out.println("What is balance? "+account.getBalance());
    }

    // a thread for adding a penny to the account
    private static class AddAPennyTask implements Runnable {
        public void run() {
            account.deposit(1);
        }
    }

    //  an inner class for account
    private static class Account {
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        //  synchronized deposit makes the balance 100
        public synchronized void deposit(int amount) {
            int newBalance = balance + amount;

            try {
                Thread.sleep(5);
            }
            catch (InterruptedException ex) {
            }

            balance = newBalance;
        }
    }
}