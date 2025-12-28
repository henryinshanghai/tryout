package com.henry.tryout.easy_coding.data_structure_and_collection_06.fail_fast_07.expect_fast_fail_01.arrayList_02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 验证：如果在 对 list中的item 进行遍历 时,更新 item的数量。则：会快速失败
// 结果：① 使用for循环，删除two时，并没有快速失败; why?
// ② 使用iterator方式，删除one时，也没有快速失败；why?
public class ArrayListFailFast {
    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        list.add("one");
        list.add("two");
        list.add("three");

        List<String> list2 = new ArrayList<>(list);

        // 1 在遍历操作时，删除列表中的元素 - 预期：抛出并发修改异常
        // 结果：打脸了，没有抛出异常 why? - 因为这里删除的是 倒数第二个元素
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
                System.out.println("移除元素后，列表的大小为：" + list.size());
            }
        }
        System.out.println(list);

        // 2 在遍历时 修改list中的item - 手段：使用 iterator对象 进行remove()操作
        // 结果：使用 iterator对象 的方式, 操作 能够顺利完成
        Iterator<String> iterator = list2.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("one".equals(item)) {
                // 🐖 不是“用了 Iterator 就能随便改”，而是“只能用 Iterator 提供的修改方法来改”。
                iterator.remove();
            }
        }
        System.out.println(list2);
    }
}
/*
实现"遍历时修改"的两种手段：
    #1 显式地使用 Iterator机制；
    #2 使用 COWList(而不是ArrayList) 参考： COWListWontFail_02
 */