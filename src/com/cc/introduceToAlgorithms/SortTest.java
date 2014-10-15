package com.cc.introduceToAlgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BearBB on 2014/10/12.
 */
public class SortTest{
    public static void main(String[] args){
        Integer[] source = new Integer[]{1,9,7,2,6,24,65,57,54,33,9};

        Util.printArray(Integer.class,InsertionSort.sortOO(source));

        Util.printArray(Integer.class,InsertionSort.sort(source));

        Util.printArray(Integer.class,SelectionSort.sortOO(source));

        Util.printArray(Integer.class,SelectionSort.sort(source));

        Util.printArray(Integer.class,MergeSort.sort(source));

        Util.printArray(Integer.class,HeapSort.sort(source));

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
    public static Integer[] sort(Integer[] source){
        new MergeSort().merge(source,0,(int)source.length/2,(int)source.length/2+1,source.length-1);
        return source;
    }

    private void merge(Integer[] source,int a,int b,int c,int d){
        Integer[] result = new Integer[d-c+a-b+2];
        if(result.length==1) return;
        int resultIndex = 0;
        for(int i=a;i<=b;){
            for(int j=c;j<=d;){
                if(source[j]>source[i]){
                    result[resultIndex++]=source[i++];
                }else{
                    result[resultIndex++]=source[j++];
                }
            }
        }
        int j=0;
        for(int i=a;i<=b;i++,j++){
            source[i] = result[j];
        }
        for(int i=c;i<=d;i++){
            source[i] = result[j];
        }
    }

    private void mergeSort(Integer[] source,int a,int b,int c,int d){
        int m = (b-a)/2;
        int m1 = (d-c)/2;
        mergeSort(source,a,m,m+1,b);
        mergeSort(source,c,m1,m1+1,d);
    }
}


class HeapSort{
    private HeapSort(){}
    private static int getLeft(int a){return 2*a;}
    private static int getRight(int a){return 2*a+1;}
    private static int getParent(int a){return a/2;}

    private void maxHeapify(Integer[] source,int a,int heapSize){
        int l = getLeft(a);
        int r = getRight(a);
        int maxIndex =a;
        if(source[l]>source[a] && l<heapSize){
            maxIndex = l;
        }
        if(source[maxIndex]<source[r] && r<heapSize){
            maxIndex = r;
        }
        if(maxIndex!=a){
            Util.swap(source,a,r);
            maxHeapify(source,maxIndex,heapSize);
        }
    }

    private void buildMaxHeap(Integer[] source){
        for(int i=getParent(source.length-1);i>=0;i--){
          maxHeapify(source,i,source.length);
        }
    }

    private void sort0(Integer[] source){
        buildMaxHeap(source);
        for(int i=source.length;i!=1;i--){
            Util.swap(source,0,i);
            maxHeapify(source,1,i);
        }
    }

    public static Integer[] sort(Integer[] source){
       new HeapSort().sort0(source);
       return  source;
    }


}

























