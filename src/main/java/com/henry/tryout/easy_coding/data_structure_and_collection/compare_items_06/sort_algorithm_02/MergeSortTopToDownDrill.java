package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.sort_algorithm_02;

public class MergeSortTopToDownDrill {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        // 初始化辅助数组的大小
        aux = new Comparable[a.length];

        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int leftBar, int rightBar) {
        if (leftBar >= rightBar) {
            return;
        }

        int middle = leftBar + (rightBar - leftBar) / 2;
        sort(a, leftBar, middle);
        sort(a, middle + 1, rightBar);

        merge(a, leftBar, middle, rightBar);
    }

    private static void merge(Comparable[] a, int leftBar, int middle, int rightBar) {
        int leftHalveCursor = leftBar;
        int rightHalveCursor = middle + 1;

        // 把 原始数组指定区间中的元素 拷贝的 辅助数组中
        for (int i = leftBar; i <= rightBar; i++) {
            // 如果aux没有做大小的初始化，这里aux会是一个null
            aux[i] = a[i];
        }

        // 对 两个有序区间 进行归并操作
        for (int i = leftBar; i <= rightBar; i++) {
            if (leftHalveCursor > middle) a[i] = aux[rightHalveCursor++];
            else if (rightHalveCursor > rightBar) a[i] = aux[leftHalveCursor++];
            else if (less(aux[leftHalveCursor], aux[rightHalveCursor])) a[i] = aux[leftHalveCursor++];
            else a[i] = aux[rightHalveCursor++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void printItem(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
    }

    public static void main(String[] args) {
        String[] a = {"H", "E", "N", "R", "Y", "A", "N", "D", "J", "I", "A", "N", "M", "E", "I"};
//        String[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);

        printItem(a);
    }
}
