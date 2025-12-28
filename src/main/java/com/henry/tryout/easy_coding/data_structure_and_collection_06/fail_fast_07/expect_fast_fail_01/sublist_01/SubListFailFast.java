package com.henry.tryout.easy_coding.data_structure_and_collection_06.fail_fast_07.expect_fast_fail_01.sublist_01;


import java.util.ArrayList;
import java.util.List;

// 验证：使用 subList()方法 从原始列表中 获取到的子列表 会有如下特性:
// ① 其CRUD的操作 会受到原始列表的影响 - 如果原始列表 发生了 元素个数相关的改动，则：branchList的CRUD 都会快速失败；
// ② 对branchList的改动 也会反映到 原始列表masterBranch上
public class SubListFailFast {
    public static void main(String[] args) {

        List masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");

        // 得到 原始list的一个子列表 -
        // 手段：list.subList(leftBar, rightBar)  特征：[leftBar, rightBar)
        List branchList = masterList.subList(0, 3);
        System.out.println(branchList.size()); // 预期：3

        /* 以下代码会导致 branchList的CRUD操作 出现异常 👇 */
        // 原因：masterList 任何 关于元素个数的修改操作 都会导致 branchList的增删改查 抛出ConcurrentModificationException
        // 为了使 对sublist的修改 不报错,这里 不能够修改 原始list 👇
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
    #1 subList子列表 无法序列化；
        原理：
            return new SubList(this, 0, fromIndex, toIndex)
            class SubList extends AbstractList<E> implements RandomAccess - 没有实现 序列化接口，所以无法序列化
    #2 subList的修改 会导致 主列表的修改；
    #3 主列表元素数量的改动 会导致 子列表的增删改查操作 抛出异常。
 */
