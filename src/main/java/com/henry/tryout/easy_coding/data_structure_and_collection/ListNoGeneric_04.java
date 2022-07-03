package com.henry.tryout.easy_coding.data_structure_and_collection;


import java.util.ArrayList;
import java.util.List;

public class ListNoGeneric_04 {
    public static void main(String[] args) {
        // 定义集合对象 - 不使用泛型
        List a1 = new ArrayList<>(); // <Object>
        a1.add(new Object());
        a1.add(new Integer(111));
        a1.add(new String("hello world"));

        // 把a1的引用赋值给a2 - 为集合元素添加泛型限制 Object
        List<Object> a2 = a1;
        a2.add(new Object());
        a2.add(new Integer(222));
        a2.add(new String("hello a2"));

        // 把a1的引用赋值给a3 - 为集合元素添加泛型限制 Integer
        // JDK5中才引入了泛型，所以这种代码是可能存在的。也可能导致一些问题
        List<Integer> a3 = a1;
        a3.add(new Integer(333));
        // 下面两行编译报错
//        a3.add(new Object());
//        a3.add(new String("hello a3"));

        // 把a1的引用赋值给a4 - 为集合元素添加泛型限制 ?通配符
        List<?> a4 = a1;
        // 特征1：允许删除与清空集合元素
        a1.remove(0);
        a4.clear();
        // 特征2：不允许增加元素
//        a4.add(new Object());

        List<Integer> intList = new ArrayList<>();
        intList.add(111);
//        List<Object> objectList = intList;
    }
}
/*
结论：
1 List<Object> 不允许赋值给 List<Integer> 变量；
2 List<Integer> 也不允许赋值给 List<Object>, 虽然 Integer肯定是一个Object，但是这种性质不会传递到列表对象。
3 List<?> 可以接受 任何类型的集合引用，不能add，但可以 remove或clear
 */
