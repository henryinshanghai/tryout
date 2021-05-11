package com.henry.tryout.gson;

import com.google.gson.Gson;

public class basicUsage_01 {

    public static void main(String[] args) {
        basicTypeExample();
    }

    private static void basicTypeExample() {
        // Serialization 从java基本数据类型 到 json对象
        Gson gson = new Gson();
        System.out.println(gson.toJson(1));;            // ==> 1
        System.out.println(gson.toJson("abcd"));;       // ==> "abcd"
        System.out.println(gson.toJson(new Long(10)));; // ==> 10
        int[] values = { 1 };
        System.out.println(gson.toJson(values));;       // ==> [1]


        // Deserialization 从 json对象 到 java基本数据类型
        int one = gson.fromJson("1", int.class);
        System.out.println(one);
//        Integer one = gson.fromJson("1", Integer.class);
//        Long one = gson.fromJson("1", Long.class);
        Boolean boolInJava = gson.fromJson("false", Boolean.class);
        System.out.println(boolInJava);

        String str = gson.fromJson("\"abc\"", String.class);
        System.out.println(str);

        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
        for (String item : anotherStr) {
            System.out.println(item);
        }
    }
}
