package com.henry.tryout.easy_coding.data_structure_and_collection_06.collection_and_generic_05.binding_action_02;

import net.sf.json.JSONObject; // 来自于 net.sf.json-lib依赖

import java.util.ArrayList;
import java.util.List;

// 验证：非泛型集合（List / JSONArray）可以赋值给任何 泛型集合 - 但可能会导致运行时的报错
// 手段：把 JSONArray类型(List的子类型, 由getJSONArray()方法返回)  绑定到 List<Integer> intList变量上
public class bindingNoGenericToGeneric {
    public static void main(String[] args) {
        JSONObject jsonObject = JSONObject.fromObject("{\"level\": [\"3\"]}"); // Inject Language技巧
        List<Integer> intList = new ArrayList<>(10);

        if (jsonObject != null) {
            // 这里把解析出来的 字符串‘3’ 添加到了 intList列表中
            // 特征： 编译时不报错，但运行时报错 - java.lang.String cannot be cast to java.lang.Integer
            /*
                原因：
                    1 getJSONArray()方法的返回值类型JSONArray；- 这里为什么一定要使用 getJSONArray()返回一个JSONArray呢？
                    2 JSONArray 是List的子类；
                    3 非泛型集合（List / JSONArray）可以赋值给任何 泛型集合;
             */
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
    1 非泛型集合(List) 可以赋值给任何 泛型集合(List<Integer>)；
    2 由于JDK在5之后才引入泛型，所以 可能会出现 非泛型对象赋值给泛型变量 的代码；
    3 在JDK5之后，应该尽量使用泛型来定义变量。
 */