package com.henry.tryout.easy_coding.data_structure_and_collection.array_and_collection_04;

// 数组的定义 & 初始化
public class ArrayUsageDemo_01 {
    public static void main(String[] args) {
        // 手段1：显式地初始化 {"xxx", "ooo"}
        String[] names1 = {"henry", "alicia"};
        // 手段2：对Object对象做强制转化
        Object obj = names1;
        ((String[]) obj)[0] = "object";

        // 手段3：声明 + 动态初始化
        String[] args4 = new String[2];
        args4[0] = "a";
        args4[1] = "b";

        // 操作：遍历数组中的元素
        for (String arg : args4) {
            System.out.println(arg);
        }
    }
}
