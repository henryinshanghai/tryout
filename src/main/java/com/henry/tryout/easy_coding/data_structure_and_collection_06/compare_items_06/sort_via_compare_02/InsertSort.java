package com.henry.tryout.easy_coding.data_structure_and_collection_06.compare_items_06.sort_via_compare_02;

/*
    算法思路描述：
        把当前元素 插入到一个有序区域中，并保持有序区域的元素有序性
    算法流程：
        1 把第一个元素视为一个 只有一个元素的有序区域；
        2 获取到有序区域后面的一个元素，把它插入到 当前的有序区域中；
            手段：在有序区域中，使用一个从后往前的游标指针。通过不断地比较与交换，把待插入元素交换到正确的预期位置中(不是最终的排定位置)
            - 1 比较有序区域的最后一个元素 与 当前待插入的元素，如果当前待插入的元素更小，就交换它们；
            - 2 比较有序区域的倒数第二个元素 与 当前待插入的元素，如果当前待插入元素更小，就交换它们；
            如此往复，直到 待插入元素已经到达预期位置 或者 待插入元素已经到达数组的起始位置
        3 对每一个 新的待插入元素，做2中同样的操作

    有意义的变量名：
        N - itemAmount;
        currentBoundary - cursorOfItemToInsert
        backwardsCursor - backwardsCursor

    严格的边界条件：
        循环条件的猜想与验证：
            猜想 - 当前场景的极限情况
            验证 - 使用猜想得到的边界条件会不会导致 下标越界
    流程中的debug：
        assert <预期目标>
 */
public class InsertSort {

    // 核心API - 对数组中的元素进行排序
    public static void sort(Comparable[] a) {
        // 插入排序     原因：从无序区域取出元素插入到有序区
        int itemAmount = a.length;

        for (int cursorOfItemToInsert = 1; cursorOfItemToInsert < itemAmount; cursorOfItemToInsert++) {
            // 把 无序区的第一个元素 插入到 有序区合适的位置
            /*
                手段：比较（当前元素 与 前一个元素） & 交换（如果当前元素更小，就执行交换）
                如果 有必要（less & exch），说明需要继续进行比较，则：把 待比较元素的指针 向前推进(j--)
                如果 没必要（less不成立），说明找到了 正确的插入位置，则：比较操作终止（for循环）
                循环条件的猜想与验证：
                    猜想：可能的最后一次比较：less(a[1], a[0]) -> 边界条件：j > 0
                    验证：使用这个循环条件会不会导致代码出现IndexOutOfBoundary的报错
             */
            for (int backwardsCursor = cursorOfItemToInsert;
                 backwardsCursor > 0 && less(a[backwardsCursor], a[backwardsCursor - 1]);
                 backwardsCursor--) {
                exch(a, backwardsCursor, backwardsCursor - 1);
            }

            // 断言 当前的有序区域 是 元素有序的
            assert isSorted(a, 0, cursorOfItemToInsert);
        }

        // 断言 整个数组 已经是有序的了
        assert isSorted(a);
    }

    // 辅助API 👇
    // 回答 指定元素序列的指定区间 是不是有序的
    private static boolean isSorted(Comparable[] a, int leftBar, int rightBar) {
        // 当前元素 一定要比 它的前一个元素 更大
        for (int cursor = leftBar + 1; cursor <= rightBar; cursor++) {
            if (less(a[cursor], a[cursor - 1])) return false;
        }

        return true;
    }

    // 对外的单参数方法
    // 回答 指定的元素序列 是否是有序的
    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    @SuppressWarnings("unchecked")
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换 指定元素序列中 指定的两个位置 上的元素
     *
     * @param a 指定的元素序列
     * @param i 指定的位置i
     * @param j 指定的位置j
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        // 在单行中打印数组
        for (int currentSpot = 0; currentSpot < a.length; currentSpot++) {
            System.out.print(a[currentSpot] + " ");
        }
        System.out.println();
    }


    // 测试用例 👇
    public static void main(String[] args) {
        // 从标准输入中 读取字符串，然后 把它们排序输出
        Integer[] a = new Integer[]{1, 4, 2, 7, 9, 0};
        sort(a);

        // 断言数组元素已经有序了
        assert isSorted(a);
        show(a);
    }
}
