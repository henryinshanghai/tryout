package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.sort_algorithm_02;

public class MergeSortTopToDownDrill02 {
    private static Comparable[] aux;

    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] a, int leftBar, int rightBar) {
        if (leftBar >= rightBar) return;

        int middle = leftBar + (rightBar - leftBar) / 2;
        sort(a, leftBar, middle);
        sort(a, middle + 1, rightBar);

        merge(a, leftBar, middle, rightBar);
    }

    private void merge(Comparable[] a, int leftBar, int middle, int rightBar) {
        int leftHalveCursor = leftBar;
        int rightHalveCursor = middle + 1;

        for (int cursor = leftBar; cursor <= rightBar; cursor++) {
            aux[cursor] = a[cursor];
        }

        // 从辅助数组aux中 拷贝元素到原始数组a中
        for (int cursor = leftBar; cursor <= rightBar; cursor++) {
            // 极端情况
            if (leftHalveCursor > middle) a[cursor] = aux[rightHalveCursor++];
            else if(rightHalveCursor > rightBar) a[cursor] = aux[leftHalveCursor++];
            // 正常地比较
            else if (less(aux[leftHalveCursor], aux[rightHalveCursor])) a[cursor] = aux[leftHalveCursor++];
            else a[cursor] = aux[rightHalveCursor++];
        }
    }

    private static void printItem(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
    }

    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        // E E G M O R R S T
        Comparable[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T"};
        MergeSortTopToDownDrill02 mergeSort = new MergeSortTopToDownDrill02();

        mergeSort.sort(a);
        printItem(a);
    }
}
