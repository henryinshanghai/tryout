package com.henry.tryout.easy_coding.data_structure_and_collection.fail_fast_07;


import java.util.ArrayList;
import java.util.List;

public class SubListFailFast_01 {
    public static void main(String[] args) {

        List masterList = new ArrayList();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");

        List branchList = masterList.subList(0, 3);
        System.out.println(branchList.size());

        // 以下代码会 - 导致branchList操作出现异常
        // #fact1 - masterList任何关于元素个数的修改操作 都会导致branchList的增删改查抛出 ConcurrentModificationException
//        masterList.remove(0);
//        masterList.add("ten");
//        masterList.clear();

        // 以下四行能够正常运行
        branchList.clear();
        branchList.add("six");
        branchList.add("seven");
        branchList.remove(0);

        // 正常遍历结束，只有一个元素： seven
        for (Object t : branchList) {
            System.out.println(t);
        }

        // 子列表修改导致主列表也被修改，输出： [seven, four, five]
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
