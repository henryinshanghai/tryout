package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.sort_algorithm_02;

public class InsertSortDrill {

    public Comparable[] sort(Comparable[] a) {
        int length = a.length;

        for (int cursorOfItemToInsert = 1; cursorOfItemToInsert < length; cursorOfItemToInsert++) {
            for (int backwardCursor = cursorOfItemToInsert; backwardCursor >=1 && less(a[backwardCursor], a[backwardCursor-1]); backwardCursor--) {
                exch(a, backwardCursor, backwardCursor - 1);
            }
        }

        return a;
    }

    private void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    public static void main(String[] args) {
        Integer[] a = {1, 5, 2, 0, 9, 4, 7, 1};
        InsertSortDrill drill = new InsertSortDrill();
        drill.sort(a);

        printArr(a);
    }

    private static void printArr(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
