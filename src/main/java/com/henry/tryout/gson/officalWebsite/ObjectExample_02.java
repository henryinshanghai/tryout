package com.henry.tryout.gson.officalWebsite;

import com.google.gson.Gson;

public class ObjectExample_02 {
    public static void main(String[] args) {

        // Serialization - 从 obj对象 得到 json字符串
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        String json = gson.toJson(obj); // 从obj -> json
        System.out.println(json);
        // ==> json is {"value1":1,"value2":"abc"}

        // Deserialization 反序列化：从json字符串 -> obj对象
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class); // 参数列表：json字符串, Java类型
        System.out.println(obj2.toString());
        // ==> obj2 is just like obj
    }
}

class BagOfPrimitives {
    private int value1 = 1;
    private String value2 = "abc";
    private transient int value3 = 3;
    BagOfPrimitives() {
        // no-args constructor
    }

    @Override
    public String toString() {
        return "BagOfPrimitives{" +
                "value1=" + value1 +
                ", value2='" + value2 + '\'' +
                ", value3=" + value3 +
                '}';
    }
}
