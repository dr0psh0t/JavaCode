package com.daryll.inheritancepolymorph;

public class DynamicBindingDemo {
    public static void main(String[] args) {
        m(new GraduateStudent());
        m(new Student());
        m(new Person1());
        m(new Object());
    }

    public static void m(Object x) {
        System.out.println(x.toString());
    }
}

class GraduateStudent extends Student {}

class Student extends Person1 {
    @Override
    public String toString() {
        return "Student";
    }
}

class Person1 extends Object {
    @Override
    public String toString() {
        return "Person";
    }
}