package com.henry.tryout.easy_coding.data_structure_and_collection_06.collection_and_generic_05.binding_action_02;


import java.util.ArrayList;
import java.util.List;

// 验证：① Object泛型集合 不能绑定到 具体类型泛型集合上 - 会有编译报错
// ② 具体类型泛型集合 也不能绑定到 Object泛型集合上 - 也会有编译报错
public class bindingObjectGenericToIntegerGeneric {
    public static void main(String[] args) {
        List<Object> a1 = new ArrayList<>();
        a1.add(new Object());
        a1.add(new Integer(111));
        a1.add(new String("hello a1a1"));

        /* 把 Object泛型的引用 绑定到 Integer泛型的引用 - 结果：编译报错 */
        // 因为 数值类型范围 收窄了，可能出现 String -> Integer的情况
//        List<Integer> a2 = a1;

        List<Integer> a3 = new ArrayList<>();
        a3.add(111);
        a3.add(222);
        a3.add(333);

        /* 如果数值类型范围 放宽呢？
            手段：把 Integer泛型的引用 绑定到 Object泛型的引用上 - 编译报错 */
        // 原因：集合不是协变的; aka, Integer is an Object, List<Integer> is not List<Object>
//        List<Object> a4 = a3;
    }
}
