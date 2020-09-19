package com.daryll.generics;

public class Testing {
    public static void main(String[] args) {

        GenericStack<String> stack1 = new GenericStack<>();
        stack1.push("asdasd");
        stack1.push("qiwey");
        stack1.push("283746");

        System.out.println(stack1);
    }
}
