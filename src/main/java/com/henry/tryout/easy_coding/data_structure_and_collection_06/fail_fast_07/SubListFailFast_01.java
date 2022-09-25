package com.henry.tryout.easy_coding.data_structure_and_collection_06.fail_fast_07;


import java.util.ArrayList;
import java.util.List;

// 验证：从原始列表中获取到的子列表 会如如下特性:
// 1 其CRUD会受到原始列表的影响 - 如果原始列表发生了元素个数相关的改动，则：branchList的CRUD都会快速失败；
// 2 对branchList的改动 也会反映到 原始列表masterBranch上
public class SubListFailFast_01 {
    public static void main(String[] args) {

        List masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");

        // 得到原始list的一个子列表 - 手段：list.subList(leftBar, rightBar)  特征：[leftBar, rightBar)
        List branchList = masterList.subList(0, 3);
        System.out.println(branchList.size()); // 预期：3

        /* 以下代码会导致branchList操作出现异常 👇 */
        // 原因：masterList任何 关于元素个数的修改操作 都会导致branchList的增删改查 抛出ConcurrentModificationException
        // 为了使 对sublist的修改不报错,这里不能够修改原始list 👇
//        masterList.remove(0);
//        masterList.add("ten");
//        masterList.clear();

        // 对subList进行 增删改的操作 - 编译器允许
        branchList.clear();
        branchList.add("six");
        branchList.add("seven");
        branchList.remove(0);

        // 遍历subList
        for (Object t : branchList) {
            System.out.println(t); // 只剩下了seven
        }

        // 打印 原始list
        // 结论： 子列表修改 导致主列表也被修改，输出： [seven, four, five]
        System.out.println(masterList);
    }
}
/*
启示：
    #1 subList子列表无法序列化；
        原理：
            return new SubList(this, 0, fromIndex, toIndex)
            class SubList extends AbstractList<E> implements RandomAccess - 没有实现 序列化接口，所以无法序列化
    #2 subList的修改会导致 主列表的修改；
    #3 主列表元素数量的改动 会导致子列表的增删改查操作抛出异常。
 */
