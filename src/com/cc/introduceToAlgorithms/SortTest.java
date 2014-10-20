package com.cc.introduceToAlgorithms;

/**
 * Created by BearBB on 2014/10/12.
 *
 * http://www.cnblogs.com/kkun/archive/2011/11/23/2260312.html
 */
public class SortTest{
    public static void main(String[] args){
        Integer[] source = new Integer[]{11,92,71,2,62,43,51,34,86,10,333,11};

        Util.printArray(Integer.class, InsertionSort.sort(source));

        Util.printArray(Integer.class, SelectionSort.sort(source));

        Util.printArray(Integer.class, MergeSort.sort(source));

        Util.printArray(Integer.class,HeapSort.sort(source));

        Util.printArray(Integer.class, BucketSort.sort(source));

        Util.printArray(Integer.class, QuickSort.sort(source));

    }
}

class InsertionSort {
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

    public static Object[] sort(Integer[] source){
        for(int j=0;j<source.length;j++){
            int index = j;
            for(int i=j;i+1<source.length;i++){
                if(source[index]>source[i]){
                    index = i;
                }
            }
            Util.swap(source, j, index);
        }
        return source;
    }
}



class MergeSort {
    private MergeSort(){}
    public static Integer[] sort(Integer[] source){
        new MergeSort().merge(source, 0, (int) source.length / 2, (int) source.length / 2 + 1, source.length - 1);
        return source;
    }

    private void merge(Integer[] source,int a,int b,int c,int d){
        Integer[] result = new Integer[d-c+a-b+2];
        if(result.length<=1) return;
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
}


class HeapSort{
    private HeapSort(){}
    private static int getLeft(int a){return 2*a;}
    private static int getRight(int a){return 2*a+1;}
    private static int getParent(int a){return a/2;}

    private void maxHeapify(Integer[] source,int i,int heapSize){
        int l = getLeft(i+1)-1;
        int r = getRight(i+1)-1;
        if(source.length<heapSize ) return;
        int largest=r;
        if(l<source.length){
            if(l<heapSize && source[l]>source[i]){
                largest = l;
            }else{
                largest = i;
            }
        }
        if(r<source.length){
            if(r<heapSize && source[r]>source[largest]){
                largest = r ;
            }
        }
        if(largest != i && largest<source.length){
            Util.swap(source, i, largest);
            maxHeapify(source,largest,heapSize);
        }
    }

    private void buildMaxHeap(Integer[] source){
        int heapSize = source.length;
        for(int i=getParent(source.length)-1;i>=0;i--){
            maxHeapify(source,i,heapSize);
        }
    }

    private void sort0(Integer[] source){
        //Util.printArrayTriangle(Arrays.asList(source));
        buildMaxHeap(source);
        //Util.printArrayTriangle(Arrays.asList(source));
        for(int i=source.length-1;i!=0;i--){
            Util.swap(source, 0, i);
            maxHeapify(source,0,i);
            //Util.printArrayTriangle(Arrays.asList(source));
        }
    }

    public static Integer[] sort(Integer[] source){
       new HeapSort().sort0(source);
       return  source;
    }

}

class BucketSort{

    public static Integer[] sort(Integer[] source){
        for(int i=1;i<=3;i++){
            soreByThePlaceOfBucketId(source,i);
        }
        return source;
    }

    private static void soreByThePlaceOfBucketId(Integer[] source, int bucketIdPlace){
        Integer[] integers = new Integer[source.length];
        int index = 0;
        for(int i=0;i<10;i++){
            for(int v:source){
                if(i == (int)Util.getCharAt(bucketIdPlace,v)){
                    integers[index++] = v;
                }
            }
        }
        source= integers;

    }

}

class QuickSort{
    public static Integer[] sort(Integer[] source){
         quickSort(source,0,source.length-1);
         return source;
    }
    private static void quickSort(Integer[] source,int p,int r){
         if(r-p>1){
             int m = partition(source,p,r);
             quickSort(source,p,m);
             quickSort(source,m+1,r);
         }else if(r-p==1){
            if(source[r]<source[p]){
                Util.swap(source,r,p);
            }
         }else{
            return;
         }
    }

    private static int partition(Integer[] source, int m,int n) {
        int v = source[m];
        int vIndex = m;
        for(int i=m+1;i<=n;i++){
            if(source[i] >= v){
                continue;
            }else{
                Util.swap(source,i,vIndex+1);
                Util.swap(source,vIndex,vIndex+1);
                m++;
            }
        }
        return vIndex;
    }


}


class CountSort{

    public static Integer[] sort(Integer[] source){
        return null;
    }

}

























