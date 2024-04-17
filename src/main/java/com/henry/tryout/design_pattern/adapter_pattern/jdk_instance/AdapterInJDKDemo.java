package com.henry.tryout.design_pattern.adapter_pattern.jdk_instance;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;

// 适配器 更像是 类型的转换器 - 适配后，原本不兼容的组件就能够通过适配器来一起工作了
// 适配的语义到底是什么？A的适配器，使得A可以适配 B   手机的适配器，使得手机能够适配 家用电源插板
public class AdapterInJDKDemo {
    public static void main(String[] args) {

        /*
            #1 java.util.Arrays.asList
            返回一个固定大小的列表（由特定的数组来支持）
            对此列表的变更 会被 “写回（write through）”到原始数组中

            这个方法（与 Collection.toArray()一起）是 基于数组的API 与 基于集合的API 之间的桥梁。
            返回的list 是可序列化的，并且实现了 RandomAccess接口
         */
        List<String> names = Arrays.asList("Henry", "Jane");


        // #2 Collections.list(<Enumeration> en)
        Stack<String> stack = new Stack<>();
        stack.push("Alicia");

        Enumeration<String> nameEnumeration = stack.elements();
        ArrayList<String> namesViaList = Collections.list(nameEnumeration);

        // #3 XmlAdapter：Adapts a Java type for custom marshaling.
        XmlAdapter xmlAdapter = new XmlAdapter() {
            /*
                把一个 value type 转换成为 bound type
             */
            @Override
            public Object unmarshal(Object v) throws Exception {
                return null;
            }

            @Override
            public Object marshal(Object v) throws Exception {
                return null;
            }
        };
    }
}

enum Seasons {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER
}
