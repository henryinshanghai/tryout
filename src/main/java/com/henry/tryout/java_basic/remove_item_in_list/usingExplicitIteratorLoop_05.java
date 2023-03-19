package com.henry.tryout.java_basic.remove_item_in_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// 验证：使用 显式的迭代器对象 来 进行列表的”遍历时删除“，能够成功达到预期效果
// 迭代器对象的用法：hasNext() + next()方法 + remove()方法
// 没抛出异常 - 迭代器的remove()方法中 对”集合的修改次数modCount“ 与 ”期待的集合修改次数expectedModCount“进行了同步
public class usingExplicitIteratorLoop_05 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("张三", "李四", "周一", "刘四", "李强", "李白");

        List<String> editableNames = new ArrayList<>(names);
        for (Iterator<String> iterator = editableNames.iterator(); iterator.hasNext(); ) {
            String name = iterator.next();
            if (name.startsWith("李")) {
                iterator.remove();
            }
        }

        System.out.println(editableNames);
    }
}
