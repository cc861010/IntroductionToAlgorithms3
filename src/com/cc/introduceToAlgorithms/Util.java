package com.cc.introduceToAlgorithms;

/**
 * Created by BearBB on 2014/10/12.
 */
public class Util {
    public static void swap(Object o1,Object o2){
       Object tmp;
       tmp = o2;
       o2 = o1;
       o1 =tmp;
    }

    public static void printArray(Class t,Object[] array){
        for(Object i:array){
            System.out.println(t.cast(i));
        }
    }
}
