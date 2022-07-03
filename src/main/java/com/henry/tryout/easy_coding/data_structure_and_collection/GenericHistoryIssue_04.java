package com.henry.tryout.easy_coding.data_structure_and_collection;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenericHistoryIssue_04 {
    public static void main(String[] args) {
        JSONObject jsonObject = JSONObject.fromObject("{\"level\": [\"3\"]}");

        List<Integer> intList = new ArrayList<>(10);

        if (jsonObject != null) {
            // 这里把解析出来的 字符串3 添加到了 intList列表中
            // 特征： 编译时不报错，但运行时报错 - java.lang.String cannot be cast to java.lang.Integer
            // 原因：getJSONArray()方法的返回值类型是 List的子类 & 非泛型集合可以赋值给任何 泛型集合
            intList.addAll(jsonObject.getJSONArray("level"));

            int amount = 0;
            for (Integer x : intList) {
                if (x < 10) {
                    amount = amount + 1;
                }
            }
        }
    }
}
/*
结论：
1 非泛型集合 可以赋值给任何 泛型集合；
2 由于JDK在5之后才引入泛型，所以 可能会出现 赋值给新的泛型列表的代码；
3 在JDK5之后，应该尽量使用泛型定义。
 */
