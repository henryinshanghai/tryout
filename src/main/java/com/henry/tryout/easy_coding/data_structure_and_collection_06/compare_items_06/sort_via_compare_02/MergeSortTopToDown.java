package com.henry.tryout.easy_coding.data_structure_and_collection_06.compare_items_06.sort_via_compare_02;

/*
    任务描述：完整地排序数组 = 把数组中的每一个元素 都排定到 其正确的位置
    算法描述：
        #1 使用归并操作 先小范围地 得到很多个 小的有序数组;
        #2 对 得到的有序数组 进行持续的归并操作 - 最终得到完整的有序数组
    实现思路：排序的任务能够 用递归的方式 解决吗？
        递归的要素：
            1 原始任务 能够分解成为 规模更小的同类型任务；
            2 解决 规模更小的任务（的结果）能够帮助解决原始任务。

        对排序任务来说, 什么是规模更小的任务？
            排序 数组的一部分

        如果排序了数组的一部分，能够帮助 “完全排序整个数组”吗？
            答：不尽然，因为即使数组的局部被排序了 但元素本身离 它的正确位置 还会有一些距离
            但局部排序的结果(小问题的答案) 有可能帮助 完全排序整个数组(原始问题的答案)， 但是必然需要其他操作的支持 - 比如手中的牌是 JQKA 2345
            对于归并排序，这里的“其他操作” ———— 归并操作。

            归并操作： 能够 把 两个有序的数组，归并得到一个更大的有序数组。
            应用： 支持 归并排序。
            方法作用： 把a[leftBar, middle] 与 a[middle+1, rightBar]两个有序的子数组进行归并，并把结果存回到a[leftBar, rightBar]中
            基于以上方法作用所设计的方法签名：
                merge(Comparable[] a, int leftBar, int middle, int rightBar)
            特征：
                这里的辅助数组，为了减少方法的参数，可以设计成为成员变量,然后在适当的方法中进行初始化即可

    public方法签名： public的方法签名 - sort(Comparable[] a) 单参数的排序方法
    内层方法签名：
        如何在方法签名层面，体现出 规模更小的排序呢？
        答：在方法签名中添加 排序区间range - 因为在做原地排序
        初步设计： sort(Comparable[] a, int leftBar, int rightBar)

    测试用例：String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
    编码易错点：
        #1 here is <=
        #2 "int cursor = 0; cursor < a.length; cursor++" is wrong, 这里应该使用的是左右区间
 */
public class MergeSortTopToDown {
    // 成员变量 - 好处：可以在 当前类的所有方法 中使用它
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        // 初始化 辅助数组的 大小
        aux = new Comparable[a.length];

        // 对 数组的指定区间 进行排序 - 这里是 整个区间范围：[0, a.length - 1]
        sort(a, 0, a.length - 1);
    }

    // 排序数组的指定区间 - a[leftBar, rightBar]闭区间
    private static void sort(Comparable[] a, int leftBar, int rightBar) {
        // 递归终结的条件：区间宽度 变窄为0
        if (leftBar >= rightBar) return;

        // 计算 当前区间的中间位置
        int middle = leftBar + (rightBar - leftBar) / 2;

        // 更小规模的问题 - 排序左半区间
        sort(a, leftBar, middle);
        // 更小规模的问题 - 排序右半区间
        sort(a, middle + 1, rightBar);

        // 使用 小规模问题的结果 帮助处理 大问题本身👇
        // 有了 两个 有序的子数组 后，使用 归并操作 来 得到一个 元素完全有序的数组
        merge(a, leftBar, middle, rightBar);
    }

    // 归并 a[leftBar, middle] 与 a[middle+1, rightBar] -
    // 前提：两个子区间都已经是有序数组了;
    // 特征：原地归并 - 归并结果 在 原始数组的空间 上
    private static void merge(Comparable[] a, int leftBar, int middle, int rightBar) {
        // 准备 左区间的指针 与 右区间的指针 - 初始都放在 区间的最左侧
        int leftHalfCursor = leftBar;
        int rightHalfCursor = middle + 1;

        // 拷贝 原始数组 闭区间[leftBar, rightBar]之间的元素 到 aux辅助数组 中
        for (int cursor = leftBar; cursor <= rightBar; cursor++) {
            aux[cursor] = a[cursor];
        }

        // 比较 aux中的左右指针所指向的元素, 并 逐个拷贝元素 到 原数组正确的位置上
        for (int currentSpotToArrange = leftBar; currentSpotToArrange <= rightBar; currentSpotToArrange++) {
            // 如果 左半区间的元素 已用尽，说明 接下来只需要 把右半区间中的元素 逐个排定到 原始数组正确的位置上 即可
            if (leftHalfCursor > middle) {
                // 则：逐个拷贝 右半边部分的元素 到 原始数组对应的位置
                a[currentSpotToArrange] = aux[rightHalfCursor++];
            }
            // 如果 右半区间中的元素 已用尽，说明 接下来只需要 把左半区间中的元素 逐个排定到 原始数组正确的位置上 即可
            else if (rightHalfCursor > rightBar) {
                // 则：逐个拷贝 左半部分的元素
                a[currentSpotToArrange] = aux[leftHalfCursor++];
            }
            // 比较 左右指针 所指向的元素，如果 左指针所指向的元素 更小，
            else if (less(aux[leftHalfCursor], aux[rightHalfCursor])) {
                // 则: ① 拷贝 左指针所指向的元素 到原数组中; ② 并 按需移动 该指针 到下一位置
                a[currentSpotToArrange] = aux[leftHalfCursor++];
            }
            // 否则，右指针所指向的元素更小（或者相等），
            else {
                // 则: ① 拷贝 右指针所指向的元素 到原数组中; ② 并 按需移动 该指针 到下一位置
                a[currentSpotToArrange] = aux[rightHalfCursor++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void printItems(Comparable[] a) {
        int N = a.length;
        for (int currentSpot = 0; currentSpot < N; currentSpot++) {
            System.out.print(a[currentSpot] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        String[] itemSequence = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(itemSequence);

        printItems(itemSequence);
    }
}
