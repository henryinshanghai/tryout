package com.henry.tryout.easy_coding.data_structure_and_collection_06.compare_items_06.equal_object_03;

import java.util.HashMap;

// 验证：HashMap中获取 指定key所关联的value 时，会使用 hashCode()与equals() 来 查找 传入的key所对应的Entry
// 手段: 向HashMap中 连续添加 逻辑上相等的key-value，查看 HashMap是否 把它识别为 相同的key
public class MapsGetMethod {
    public static void main(String[] args) {

        HashMap<Object, Object> map = new HashMap<>();

        // henry1 和 henry2 这两个对象 会被 HashMap 视为 相同的对象吗?
        EqualsObject henry1 = new EqualsObject(1, "henry");
        EqualsObject henry2 = new EqualsObject(1, "henry");

        // 以 henry1作为key 添加键值对
        map.put(henry1, "jane");
        // 再以henry2作为key 添加键值对
        map.put(henry2, "jack");

        // 从哈希结构中 获取 key为henry的键值对 中的value的值
        /*
        getNode(hash(key), key)

        if (e.hash == hash && // #1 先决条件: 计算的hash值 与 Entry的key的hash值相等 - 只有true时，才会执行后继的代码
            ((k = e.key) == key || (key != null && key.equals(k)))) // #2 要么是对象地址相等, 要么是 对象的值相等
            return e;

            哈希算法的追求：在equals()不相等时，hashCode也不相等 - 这样能够提升👆面代码的执行效率
                1 使用自定义的对象作为map的key时，需要重写 hashCode() 与 equals()方法
                2 使用自定义的对象 作为set的元素时，也需要重写 hashCode() 与 equals()方法 - 这两个方法会被用来对象的判等
         */
        Object o = map.get(henry1);
        System.out.println(o); // jack 说明HashMap把 henry2作为key的键值对 视为一个 已经存在的key，做了 更新操作
    }
}
