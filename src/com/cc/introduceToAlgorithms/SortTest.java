package com.cc.introduceToAlgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BearBB on 2014/10/12.
 */
public class SortTest{
    public static void main(String[] args){
        Integer[] source = new Integer[]{1,9,7,2,6,24,65,57,54,33,14};

        Util.printArray(Integer.class,InsertionSort.sortOO(source));

        Util.printArray(Integer.class,InsertionSort.sort(source));

        Util.printArray(Integer.class,SelectionSort.sortOO(source));

        Util.printArray(Integer.class,SelectionSort.sort(source));

    }
}

class InsertionSort {
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
        int tmp;
        for(int i=2;i<source.length;i++){
           for(int j=i;j-1>=0;j--){
              if(source[j-1]>source[j]){
                  tmp = source[j-1];
                  source[j-1] = source[j];
                  source[j] = tmp;
              }
           }
        }
        return source;
    }

}


class SelectionSort {
    private SelectionSort(){}
    public Integer getMinValueIndex(Integer[] source, int begin){
        Integer tmp = -1;
        for(int i=begin;i+1<source.length;i++){
            if(source[i]<source[i+1]){
                tmp = i;
            }else{
                tmp = i+1;
            }
        }
        return tmp;
    }

    public Integer[] moveMinValueHead(Integer[] source){
       for(int i=0;i+1<source.length;i++){
           Util.swap(source,i,getMinValueIndex(source,i));
       }
       return source;
    }


    public static Integer[] sortOO(Integer[] source){
        SelectionSort selectionSort = new SelectionSort();
        return selectionSort.moveMinValueHead(source);
    }


    public static Object[] sort(Integer[] source){
        for(int j=0;j<source.length;j++){
            int value = source[j];
            int index = j;
            for(int i=j;i+1<source.length;i++){
                if(value<source[i+1]){
                    index = i;
                    value = source[i];
                }else{
                    index = i+1;
                    value = source[i+1];
                }
            }
            Util.swap(source,j,index);
        }
        return source;
    }
}





























