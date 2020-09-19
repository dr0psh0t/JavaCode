package com.daryll.generics;

public class GenericSort {

    public static void main(String[] args)
    {
        Integer[] intArray = {2, 4, 3};

        Double[] doubleArray = {3.4, 1.3, -22.1};

        Character[] charArray = {'a', 'J', 'r'};

        String[] stringArray = {"Tom", "Susan", "Kim"};

        sort(intArray); //  intArray is the 'E' of type Integer which implements the Comparable interface
        sort(doubleArray);
        sort(charArray);
        sort(stringArray);

        System.out.println("After Sorting");
        printList(intArray);
        printList(doubleArray);
        printList(charArray);
        printList(stringArray);
    }

    public static <E extends Comparable<E>> void sort(E[] list)
    {
        E currentMin;
        int currentMinIndex;

        for (int i = 0; i < list.length - 1; i++)
        {
            currentMin = list[i];
            currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++)
            {
                if (currentMin.compareTo(list[j]) > 0)
                {
                    currentMin = list[j];
                    currentMinIndex = j;
                }

                if (currentMinIndex != i)
                {
                    list[currentMinIndex] = list[i];
                    list[i] = currentMin;
                }
            }
        }
    }

    public static void printList(Object[] list)
    {
        for (int i = 0; i <list.length; i++)
        {
            System.out.print(list[i]+" ");
        }
        System.out.println();
    }
}
