package com.cc.introduceToAlgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BearBB on 2014/10/12.
 */


public class InsertionSort {
    private InsertionSort(){};
    public static class SortedPart{
        public List<Integer> list = new ArrayList<Integer>();
        public void addValue(int value){
            list.add(value);
            swapIfLessTheFrontValueFromEnd();
        }
        private void swapIfLessTheFrontValueFromEnd(){
            for(int j=list.size()-1;j>=1;j--){
                if(list.get(j)<list.get(j-1)){
                    int tmp = list.get(j);
                    list.set(j,list.get(j-1));
                    list.set(j-1,tmp);
                }
            }
        }
    }
    public static Object[] sortOO(Integer[] source){
        SortedPart sortedPart = new SortedPart();
        for(Integer i:source){
            sortedPart.addValue(i);
        }
        return sortedPart.list.toArray();
    }

    public static Object[] sort(Integer[] source){
        for(int i=2;i<source.length;i++){

        }
        return new Object[1];
    }

    public static void main(String[] args){
        Object[] integers = InsertionSort.sortOO(new Integer[]{1,9,7,2,6,24,65,57,54,33,14});
        for(Object i:integers){
           System.out.println((Integer)i);
        }
    }
}
