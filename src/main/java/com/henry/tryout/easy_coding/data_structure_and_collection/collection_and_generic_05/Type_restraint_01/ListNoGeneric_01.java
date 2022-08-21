package com.henry.tryout.easy_coding.data_structure_and_collection.collection_and_generic_05.Type_restraint_01;

import com.henry.tryout.easy_coding.data_structure_and_collection.collection_framework_02.arraylist_01.JDKArrayList_01;

import java.util.List;

public class ListNoGeneric_01 {
    public static void main(String[] args) {

        // 没有泛型之前，定义集合的方式
        // 特征： List没有做任何的类型限制 - 这种做法很危险
        List a1 = new JDKArrayList_01();
        a1.add(new Object());
        a1.add(new Integer(111));
        a1.add(new String("hello a1a1"));

        /* 添加 Object的泛型限制 👇 */
        // 特征：什么样类型的元素都可以向 list中添加
        List<Object> a2 = a1;
        a2.add(new Object());
        a2.add(new Integer(222));
        a2.add(new String("hello a2a2"));

        /* 添加 Integer的泛型限制 👇 */
        // 特征：编译器只允许 向list中添加 Integer类型的元素
        List<Integer> a3 = a1;
        a3.add(new Integer(333));
        // 添加了泛型限制后，不再允许添加 非Integer类型的item进入集合
//        a3.add(new Object());
//        a3.add(new String("hello a3a3"));

        /* 添加 ?的泛型限制 - 通配符限制 */
        // 特征：允许删除和清空元素，但不允许添加任何元素
        List<?> a4 = a1;
        a1.remove(0);
        a4.clear();
//        a4.add(new Object());
    }
}
/*
启示：
     1 ?在正则表达式中 可以匹配任何字符；
     2 List<?> 类型变量 能够接受 任何类型的集合引用；
     3 List<?> 变量，不能够添加元素，但可以remove() 与 clear()。
     List<?>应用：
        1 作为参数类型 接收外部传入的集合；
        2 返回一个 不知道元素类型具体是什么的集合。
 */
