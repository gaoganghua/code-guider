package com.algorithm.bean;

public class HeapSortBean {
    public static void main(String[] args) {
        int[] arrs = {1, 5, 9, 4, 3, 7, 0, 9, 5, 6};

        heapsort(arrs);
        for(int i:arrs){
            System.out.print(i+",");
        }
    }
    public static void heapsort(int[] arrs){
        int n = arrs.length;
        for(int i=(n/2)-1;i>=0;i--)
//            adjustHeap1(arrs, i, n);
            adjustHeap2(arrs, i, n);
        for(int i=n-1;i>=0;i--){
            int t = arrs[i];
            arrs[i] = arrs[0];
            arrs[0] = t;
//            adjustHeap1(arrs, 0, i);
            adjustHeap2(arrs, 0, i);
        }
    }
    public static void adjustHeap1(int[] arrs, int i, int len){
        int k=i*2+1;

        if(k+1<len && arrs[k]<arrs[k+1])
            k=k+1;
        if(k<len && arrs[k]>arrs[i]) {
            int t = arrs[i];
            arrs[i] = arrs[k];
            arrs[k] = t;
            adjustHeap1(arrs, k, len);
        }
    }
    public static void adjustHeap3(int[] arrs, int i, int len){
        int tmp = arrs[i];
        for(int k=i*2+1;k<len;k=k*2+1) {
            if (k+1<len && arrs[k]<arrs[k + 1])
                k = k + 1;
            if (k<len && arrs[k] > tmp) {
                arrs[i] = arrs[k];
                i=k;
            }else{
                break;
            }
        }
        arrs[i] = tmp;
    }
    public static void adjustHeap2(int[] arrs, int i, int len){
        int tmp = arrs[i];
        int k=i*2+1;
        while(k<len){
            if(k+1<len && arrs[k]<arrs[k+1])
                k++;
            if(tmp<arrs[k]){
                arrs[i] = arrs[k];
                i=k;
                k=k*2+1;
            }else
                break;
        }
        arrs[i] = tmp;
    }

}

