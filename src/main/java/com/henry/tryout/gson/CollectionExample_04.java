package com.henry.tryout.gson;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public class CollectionExample_04 {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Collection<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);

        // Serialization collection -> jsonObj
        String json = gson.toJson(ints);  // ==> json is [1,2,3,4,5]
        System.out.println(json);

        // Deserialization jsonObj -> collection
        // 准备一个Collection类型； - 由于Collection作为容器可能会存放任意类型的元素，所以这里的手段是：TypeToken()
        // 语法： Type xxx = new TypeToken<Collection<ooo>>(){}.getType()
        Type collectionType = new TypeToken<Collection<Integer>>(){}.getType();
        Collection<Integer> ints2 = gson.fromJson(json, collectionType);
        // ==> ints2 is same as ints
        System.out.println(ints2);
    }
}
/*
启示：
    相当可怕：请注意我们如何定义 集合的类型(可怕的语法) 。不幸的是，Java没有办法解决(get around)这个问题。

    Gson可以序列化 任意对象的集合，但不能对其进行反序列化 - 因为用户无法指示生成的对象的类型。
    相反，在反序列化时，Collection必须是特定的通用类型。
    这是有道理的，并且在遵循良好的Java编码惯例时很少出现问题。
 */