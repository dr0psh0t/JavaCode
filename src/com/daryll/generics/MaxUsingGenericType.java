package com.daryll.generics;

public class MaxUsingGenericType {

    public static <E extends Comparable<E>> E max(E o1, E o2)
    {
        if (o1.compareTo(o2) > 0) {
            return o1;
        }
        else {
            return o2;
        }
    }

    public static void main(String[] args)
    {
        //System.out.println(max("Welcome", "Asdqwe"));

        Dog dog = new Dog("qwe");
        Dog dog1 = new Dog("zee");

        System.out.println(max(dog, dog1));
    }
}

//  test only
class Dog implements Comparable<Dog>
{
    String name;

    public Dog(String nme) {
        name = nme;
    }

    @Override
    public int compareTo(Dog dog1)
    {
        return name.compareTo(dog1.name);
    }

    @Override
    public String toString() {
        return name;
    }
}