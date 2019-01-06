package com.algorithm.bean;

import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

class Node{
    public int data;
    public Node left;
    public Node right;
}
public class TreeBean {
    public static void main(String[] args) {
        Node root = initTree();

//        printTree(root, "-");
        nonPrintTree(root, "-");
        iterator(root);
    }
    public static Node initTree() {
        Node n = new Node();

        int value = new Scanner(System.in).nextInt();
        if (value < 0)
            return null;
        n.data = value;
        n.left = initTree();
        n.right = initTree();
        return n;
    }
    //层序遍历
    public static void iterator(Node root){
        LinkedBlockingQueue<Node> q = new LinkedBlockingQueue<>();
        try {
            q.put(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(!q.isEmpty()){
            Node n = q.poll();
            if(n==null)
                continue;
            System.out.print(n.data + ",");
            try {
                if(n.left!=null)
                    q.put(n.left);
                if(n.right!=null)
                    q.put(n.right);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    //前序遍历
    public static void printTree(Node n, String s){
        if(n==null)
            return ;
        System.out.println(s+n.data);
        s = s+"-";
        printTree(n.left, s);
        printTree(n.right, s);
    }
    //前序遍历(非递归)
    public static void nonPrintTree(Node n, String s){
        Stack<Node> stack = new Stack<>();
        Node p = n;
        while(p!=null || !stack.isEmpty()){
            if(p!=null){
                stack.push(p);
                System.out.print(p.data+",");
                p = p.left;
            }else{
                p = stack.pop();
                p = p.right;
            }
        }
    }
}