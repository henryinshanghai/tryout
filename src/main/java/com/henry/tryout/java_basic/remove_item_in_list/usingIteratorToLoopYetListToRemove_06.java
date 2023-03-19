package com.henry.tryout.java_basic.remove_item_in_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// 验证：当 使用迭代器进行循环，而使用列表对象本身进行remove 的方式 来 执行列表”遍历时删除“的操作时，会抛出 并发修改异常
// 原理： list的remove()方法中， 没有同步 modCount 与 expectedModCount的值
public class usingIteratorToLoopYetListToRemove_06 {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("张三", "李四", "周一", "刘四", "李强", "李白");

        List<String> editableNames = new ArrayList<>(names);
        for (Iterator<String> iterator = editableNames.iterator(); iterator.hasNext(); ) {
            String name = iterator.next();
            if (name.startsWith("李")) {
                editableNames.remove(name);
            }
        }

        System.out.println(editableNames);

    }
}
