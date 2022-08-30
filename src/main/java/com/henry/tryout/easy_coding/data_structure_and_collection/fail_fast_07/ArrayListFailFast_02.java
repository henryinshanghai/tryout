package com.henry.tryout.easy_coding.data_structure_and_collection.fail_fast_07;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// 验证：在对list中的item进行遍历时,更新item的数量。则：会快速失败
public class ArrayListFailFast_02 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        list.add("one");
        list.add("two");
        list.add("three");

        List<String> list2 = new ArrayList<>(list);

        // 在遍历操作时，删除列表中的元素 - 预期：抛出并发修改异常
        // 结果：打脸了，没有抛出异常 why?
        /*
            hasNext() - return cursor != size;
            next() - next() { checkForComodification(); ...}
            remove(item) - fastRemove(index); - System.arraycopy(elementData, index+1, elementData, index, numMoved);
            #1 在remove(item)时，会：1 把所有的元素往前拷贝； 2 更新size变量的值；
            #2 在执行hasNext()时，因为: cursor == size, 因此 结果为false
            #3 没有机会执行到next()的第一行代码，从而抛出 并发修改异常~~
         */
        for (String s : list) {
            if ("two".equals(s)) { // 巧了，如果这里换成 one 或者 three, 都会引起并发修改异常 ConcurrentModificationException
                list.remove(s);
            }
        }

        List<String> cowList = new CopyOnWriteArrayList<>();
        Iterator<String> cowIterator = cowList.iterator();

        // 在遍历时修改list中的item - 手段：使用iterator对象进行remove()操作
        Iterator<String> iterator = list2.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("one".equals(item)) {
                iterator.remove();
            }
        }
        System.out.println(list2);
    }
}
/*
结论：
    #1 可以使用Iterator 机制 来进行 "遍历时的删除"；- 注：如果是多线程并发，还需要在 Iterator遍历时加锁
 */