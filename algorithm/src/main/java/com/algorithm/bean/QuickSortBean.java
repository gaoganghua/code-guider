package com.algorithm.bean;

import java.util.*;

public class QuickSortBean {
    public static void main(String[] args) {
        int[] arrs = new int[]{2, 6, 9, 1, 3, 7, 2, 0, 1};

//        quicksort(arrs, 0, arrs.length-1);
//        notcurr(arrs, 0, arrs.length-1);
//        for(int i:arrs){
//            System.out.print(i+",");
//        }
        Random r = new Random();
        List<Integer> list = new ArrayList();
        for(int i=0;i<50;i++){
            list.add(r.nextInt(15));
        }
        int i=0;
        Collections.sort(list);
    }
    public static void notcurr(int[] arrs, int start, int end){
        Stack<int[]> s = new Stack<int[]>();

        int mid = sort2(arrs, start, end);

        s.push(new int[]{mid+1, end});
        s.push(new int[]{start, mid-1});
        while(!s.empty()){
            int[] ss = s.pop();
            if(ss[0]>=ss[1])
                continue;
            mid = sort2(arrs, ss[0], ss[1]);
            s.push(new int[]{mid+1, ss[1]});
            s.push(new int[]{ss[0], mid-1});
        }
    }
    public static void quicksort(int[] arrs, int start, int end){
        if(start>=end)
            return;
//        int mid = sort1(arrs, start, end);
        int mid = sort2(arrs, start, end);
//        int mid = sort3(arrs, start, end);
        quicksort(arrs, start, mid-1);
        quicksort(arrs, mid+1, end);
    }
    public static int sort3(int[] arrs, int start, int end){
        int p=start;
        int q = start+1;
        int tmp = arrs[p];
        while(q<=end) {
            while(tmp>=arrs[q] && ++p!=q){
                int t = arrs[p];
                arrs[p] = arrs[q];
                arrs[q] = t;
            }
            q++;
        }
        arrs[start] = arrs[p];
        arrs[p] = tmp;
//        int t = arrs[start];
//        arrs[start] = arrs[p];
//        arrs[p] = t;
        return p;
    }
    public static int sort2(int[] arrs, int start, int end){
        int i=start;
        int j=end;

        int tmp = arrs[i];
        while(i<j) {
            while (arrs[j] >= tmp && i<j) {
                j--;
            }
            arrs[i]=arrs[j];
            while (arrs[i] <= tmp && i<j){
                i++;
            }
            arrs[j]=arrs[i];
        }
        arrs[i]=tmp;
        return i;
    }
    public static int sort1(int[] arrs, int start, int end){
        int i=start,j=end;

        int tmp = arrs[i];
        while(i<j) {
            while (arrs[j] >= tmp && i<j)
                j--;
            while (arrs[i] <= tmp && i<j)
                i++;
//            swap(arrs[i], arrs[j]);
//            int t = arrs[i];
//            arrs[i] = arrs[j];
//            arrs[j] = t;
        }
//        swap(arrs[i], arrs[start]);
//        int t = arrs[i];
//        arrs[i] = arrs[start];
//        arrs[start] = t;
        return i;
    }
}