package com.cc.introduceToAlgorithms;

/**
 * Created by BearBB on 2014/10/12.
 */
public class Util {
    public static void swap(Object[] array, int i, int j) {
        Object tmp;
        if((array.length-1)>=i && (array.length-1)>=j ){
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void printArray(Class t, Object[] array) {
        for (Object i : array) {
            System.out.print(t.cast(i)+",");
        }
        System.out.println();
    }
}
