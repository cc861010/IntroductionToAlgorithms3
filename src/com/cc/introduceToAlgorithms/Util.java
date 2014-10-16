package com.cc.introduceToAlgorithms;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by BearBB on 2014/10/12.
 */
public class Util {
    public static void swap(Object[] array, int i, int j) {
        if(i==j) return;
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

    public static <T extends Comparable<?>> void printArrayTriangle(List<T> list){
        BTreePrinter.printTriangle(list);
    }

    private static class BTreePrinter {
        static class Node<T extends Comparable<?>> {
            Node<T> left, right;
            T data;
            public Node(){}
            public Node(T data) {
                this.data = data;
            }
        }

        private static <T extends Comparable<?>> void setNode(Node node,int i,List<T> list){
            if(i>=0 && i<list.size()){
                node.data = list.get(i);
            }else{
                return;
            }
            int leftIndex = 2*(i+1)-1;
            int rightIndex = 2*(i+1)+1-1;
            if(leftIndex < list.size()){
                node.left = new Node<T>();
                setNode(node.left,leftIndex,list);
            }
            if(rightIndex < list.size()){
                node.right = new Node<T>(list.get(rightIndex));
                setNode(node.right,rightIndex,list);
            }
        }
        public static <T extends Comparable<?>> void printTriangle(List<T> list) {
            Node<T> root = new Node<T>();
            setNode(root,0,list);
            printNode(root);
        }

        public static <T extends Comparable<?>> void printNode(Node<T> root) {
            int maxLevel = BTreePrinter.maxLevel(root);
            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<Node<T>> newNodes = new ArrayList<Node<T>>();
            for (Node<T> node : nodes) {
                if (node != null) {
                    System.out.print(node.data);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
            if (node == null)
                return 0;
            return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }
            return true;
        }

    }



    private static class Node<T extends Comparable<T>> {
        T value;
        Node<T> left, right;

        public void insertToTree(T v) {
            if (value == null) {
                value = v;
                return;
            }
            if (v.compareTo(value) < 0) {
                if (left == null) {
                    left = new Node<T>();
                }
                left.insertToTree(v);
            } else {
                if (right == null) {
                    right = new Node<T>();
                }
                right.insertToTree(v);
            }
        }

        public void printTree(OutputStreamWriter out) throws IOException {
            if (right != null) {
                right.printTree(out, true, "");
            }
            printNodeValue(out);
            if (left != null) {
                left.printTree(out, false, "");
            }
        }
        private void printNodeValue(OutputStreamWriter out) throws IOException {
            if (value == null) {
                out.write("<null>");
            } else {
                out.write(value.toString());
            }
            out.write('\n');
        }
        // use string and not stringbuffer on purpose as we need to change the indent at each recursion
        private void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
            if (right != null) {
                right.printTree(out, true, indent + (isRight ? "        " : " |      "));
            }
            out.write(indent);
            if (isRight) {
                out.write(" /");
            } else {
                out.write(" \\");
            }
            out.write("----- ");
            printNodeValue(out);
            if (left != null) {
                left.printTree(out, false, indent + (isRight ? " |      " : "        "));
            }
        }
    }


}
