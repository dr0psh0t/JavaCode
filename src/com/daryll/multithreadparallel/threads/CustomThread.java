package com.daryll.multithreadparallel.threads;

public class CustomThread extends Thread {
    private String text;

    public CustomThread(String str) {
        text = str;
    }

    @Override
    public void run() {
        System.out.println(text);
    }
}
