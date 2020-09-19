package com.daryll.multithreadparallel.runnables;

public class TaskClass implements Runnable {

    public TaskClass() {}

    public void run() {
        System.out.println("Task is running.");
    }
}
