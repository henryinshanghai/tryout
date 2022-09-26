package com.henry.tryout.easy_coding.data_structure_and_collection_06.array_and_collection_04;

// 语法：数组的定义 & 初始化
public class ArrayUsageDemo_01 {
    public static void main(String[] args) {
        // 定义数组手段1：静态初始化/一次性初始化 {"xxx", "ooo"}
        String[] names1 = {"henry", "alicia"};
        // 定义数组手段2：对Object对象 做强制转化
        Object obj = names1;
        ((String[]) obj)[0] = "object";

        // 定义数组手段3：声明 + 动态初始化
        String[] args4 = new String[2];
        args4[0] = "a";
        args4[1] = "b";

        // 操作：遍历数组中的元素
        for (String arg : args4) {
            System.out.println(arg);
        }
    }
}
