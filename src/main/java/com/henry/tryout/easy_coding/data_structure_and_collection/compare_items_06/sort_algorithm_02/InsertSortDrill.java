package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.sort_algorithm_02;

public class InsertSortDrill {

    public static Comparable[] sort(Comparable[] a) {
        int itemAmount = a.length;

        for (int anchorOfItemToInsert = 1; anchorOfItemToInsert < itemAmount; anchorOfItemToInsert++) {
            for (int backwardCursor = anchorOfItemToInsert; backwardCursor >= 1 && less(a[backwardCursor], a[backwardCursor-1]); backwardCursor--) {
                exch(a, backwardCursor, backwardCursor - 1);
            }

            // 断言有序区间
            assert isSorted(a, 0, anchorOfItemToInsert);
        }

        assert isSorted(a);
        return a;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void printArr(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
    }

    // 断言 数组a已经排序
    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // 断言 数组a的指定区间已经有序了
    private static boolean isSorted(Comparable[] a, int leftBar, int rightBar) {
        for (int cursor = leftBar; cursor <= rightBar; cursor++) {
            if (less(a[cursor], a[cursor - 1])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Integer[] a = {2, 5, 1, 9, 3, 5, 7, 0};

        sort(a);
        assert isSorted(a);

        printArr(a);
    }
}
