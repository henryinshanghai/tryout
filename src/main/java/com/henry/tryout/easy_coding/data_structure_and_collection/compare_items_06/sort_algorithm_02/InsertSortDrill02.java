package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.sort_algorithm_02;

// 使用插入排序的算法 来 对数组元素进行排序
public class InsertSortDrill02 {

    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public void sort(Comparable[] a, int leftBar, int rightBar) {
        int amount = a.length;

        for (int anchorOfItemToInsert = 1; anchorOfItemToInsert < amount; anchorOfItemToInsert++) {
            for (int backwardsCursor = anchorOfItemToInsert; backwardsCursor > 0 && less(a[backwardsCursor], a[backwardsCursor - 1]);
                 backwardsCursor--) {
                exch(a, backwardsCursor, backwardsCursor - 1);
            }

            assert isSorted(a, 0, anchorOfItemToInsert);
        }
        assert isSorted(a);
    }

    private void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void printItem(Comparable[] a) {
        for (Comparable item : a) {
            System.out.print(item + " ");
        }
    }

    public boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private boolean isSorted(Comparable[] a, int leftBar, int rightBar) {
        for (int i = leftBar; i < rightBar; i++) {
            if (less(a[i + 1], a[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Comparable[] a = {"I", "N", "S", "E", "R", "T", "S", "O", "R", "T"};
        InsertSortDrill02 insertSort = new InsertSortDrill02();

        insertSort.sort(a);
        printItem(a);
    }
}
