package com.henry.tryout.easy_coding.data_structure_and_collection_06.compare_items_06.equal_object_03;

import java.util.HashMap;

// 验证：hashmap中获取指定key对应的value时，会使用 hashCode()与equals()来找到 传入的key所对应的Entry
public class MapsGetMethod {
    public static void main(String[] args) {

        HashMap<Object, Object> map = new HashMap<>();

        // 向哈希结构中添加一个 henry-jane的元素
        map.put("henry", "jane");

        // 从哈希结构中获取 key为henry的键值对 所对应的value的值
        /*
        getNode(hash(key), key)

        if (e.hash == hash && // #1 先决条件: 计算的hash值 与 Entry的key的hash值相等 - 只有true时，才会执行后继的代码
            ((k = e.key) == key || (key != null && key.equals(k)))) // #2 要么是对象地址相等, 要么是 对象的值相等
            return e;

            哈希算法的追求：在equals()不相等时，hashCode也不相等 - 这样能够提升👆面代码的执行效率
                1 使用自定义的对象作为map的key时，需要重写 hashCode() 与 equals()方法
                2 使用自定义的对象 作为set的元素时，也需要重写 hashCode() 与 equals()方法 - 这两个方法会被用来对象的判等
         */
        map.get("henry");
    }
}
