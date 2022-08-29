package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.sort_algorithm_02;

public class MergeSortTopToDownDrill {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int leftBar, int rightBar) {
        if (leftBar >= rightBar) return;

        int middle = leftBar + (rightBar - leftBar) / 2;
        sort(a, 0, middle);
        assert isSorted(a, 0, middle);

        sort(a, middle + 1, rightBar);
        assert isSorted(a, middle + 1, rightBar);

        merge(a, leftBar, middle, rightBar);
        assert isSorted(a, 0, a.length - 1);
    }

    private static void merge(Comparable[] a, int leftBar, int middle, int rightBar) {
        for (int cursor = leftBar; cursor <= rightBar; cursor++) {
            aux[cursor] = a[cursor];
        }

        int leftHalfCursor = leftBar;
        int rightHalfCursor = middle + 1;

        for (int cursor = leftBar; cursor <= rightBar; cursor++) {
            if (leftHalfCursor > middle) a[cursor] = aux[rightHalfCursor++];
            else if(rightHalfCursor > rightBar) a[cursor] = aux[leftHalfCursor++];
            else if (less(aux[leftHalfCursor], aux[rightHalfCursor])) a[cursor] = aux[leftHalfCursor++];
            else a[cursor] = aux[rightHalfCursor++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void printItems(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
    }

    private static boolean isSorted(Comparable[] a, int leftBar, int rightBar) {
        for (int cursor = leftBar; cursor <= rightBar; cursor++) {
            if (less(a[leftBar + 1], a[leftBar])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] a = {"H", "E", "N", "R", "Y", "A", "N", "D", "J", "A", "N", "E"};
        sort(a);

        printItems(a);
    }
}
