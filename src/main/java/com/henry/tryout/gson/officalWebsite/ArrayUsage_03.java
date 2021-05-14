package com.henry.tryout.gson.officalWebsite;

import com.google.gson.Gson;

public class ArrayUsage_03 {
    public static void main(String[] args) {
        Gson gson = new Gson();
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};

        // Serialization obj对象 -> json字符串
        System.out.println(gson.toJson(ints));;     // ==> [1,2,3,4,5]
        System.out.println(gson.toJson(strings));;  // ==> ["abc", "def", "ghi"]

        // Deserialization json字符串 -> obj对象
        int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class); // 参数列表：json字符串(数组), 数组类型的class对象
        // ==> ints2 will be same as ints
        for (int item : ints2) {
            System.out.println(item);
        }
    }
}
/*
    我们还支持具有任意复杂元素类型的多维数组。
 */
