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
        int index = begin;
        for(int i=begin;i+1<source.length;i++){
            if(source[index]>source[i]){
                index = i;
            }
        }
        return index;
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
            int index = j;
            for(int i=j;i+1<source.length;i++){
                if(source[index]>source[i]){
                    index = i;
                }
            }
            Util.swap(source,j,index);
        }
        return source;
    }
}



class MergeSort {
    private MergeSort(){}
    public void sore(Integer[] source){
        switch (source.length){
            case 1:break;
            case 2:
                if(source[0]>source[1]) Util.swap(source,0,1);
                break;
            default:
                int a = (source.length%2==0)?source.length/2:source.length+1;
                Integer[] left = new Integer[a];
                Integer[] right = new Integer[source.length-a];
                for(int i=0;i<source.length;i++){
                    if(i<left.length){
                        left[i] = source[i];
                    }else{
                        right[i-left.length] = source[i];
                    }
                }


        }

    }

    private void merge(Integer[] source,int a,int b,int c,int d){
        if(a==b && c==d){
           if(source[a]>source[c]){
               Util.swap(source,a,c);
           }
        }else{
            int a1,b1,c1,d1;
            if((b-a)%2==0){
                a1 = a+(b-a)/2;
                b1 = b-(b-a)/2;
            }else{
                a1 = a+(int)(b-a)/2+1;
                b1 = b-(int)(b-a)/2-1;
            }
            merge(source,a,a1,b1,b);
            if((d-c)%2==0){
                c1 = c+(d-c)/2;
                d1 = d-(d-c)/2;
            }else{
                c1 = c+(int)(d-c)/2+1;
                d1 = d-(int)(d-c)/2-1;
            }
            merge(source,c,c1,d1,d);
        }

    }
}

























