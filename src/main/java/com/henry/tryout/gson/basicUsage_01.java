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
/*
启示：
    1 使用私有字段非常好（并且推荐）。
    2 无需使用任何注释来指示要包括要进行序列化和反序列化的字段。
        默认情况下，包括当前类中的所有字段（以及所有超类中的所有字段）。
    3 如果字段标记为 transient，（默认情况下）该字段将被忽略，并且不包含在JSON序列化或反序列化中。
    4 此实现可正确处理null。
        - 序列化时，输出中将省略空字段。
        - 反序列化时，JSON中缺少条目会导致将对象中的相应字段设置为其默认值：
            对象类型为null，数字类型为零，布尔值为false。
    5 如果字段是 synthetic字段，则将其忽略，并且不包含在JSON序列化或反序列化中。
    6 与内部类，匿名类和局部类中的外部类相对应的字段将被忽略，并且不包含在序列化或反序列化中。

 */
