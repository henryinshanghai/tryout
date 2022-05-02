package com.henry.tryout.java_8_features.refactor_test_debug_04.test_lambda_expression_03;

import java.util.ArrayList;
import java.util.List;

public class high_level_function {

    // 高阶函数 - 传入一个 元素为泛型类型的列表 与 一个返回boolean类型的Lambda表达式
    // 测试：High_Level_Lambda_Expression_Test.java
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();

        for (T e : list) {
            if (p.test(e)) { // 过滤
                result.add(e);
            }
        }

        return result;
    }
}


