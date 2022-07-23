package com.henry.tryout.easy_coding.data_structure_and_collection.array_and_collection_04;

public class ArrayDemo_01 {
    public static void main(String[] args) {
        String[] names1 = {"henry", "alicia"};
        // 绑定到 Object类型的变量上
        Object obj = names1;
        // 从Object类型 强制转换成为 String[]
        ((String[]) obj)[0] = "object";

        // 声明 + 静态初始化
        String[] args3 = {"a", "b"};

        // 声明 + 动态初始化
        String[] args4 = new String[2];
        args4[0] = "a";
        args4[1] = "b";

        // 遍历数组中的元素
        for (String arg : args4) {
            System.out.println(arg);
        }
    }
}
