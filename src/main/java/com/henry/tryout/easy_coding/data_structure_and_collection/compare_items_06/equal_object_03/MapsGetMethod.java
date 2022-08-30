package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.equal_object_03;

import java.util.HashMap;

public class MapsGetMethod {
    public static void main(String[] args) {

        HashMap<Object, Object> map = new HashMap<>();

        map.put("henry", "jane");

        /*
        getNode(hash(key), key)

        if (e.hash == hash &&
            ((k = e.key) == key || (key != null && key.equals(k))))
            return e;

            e.hash == hash // 先决条件，只有true时，才会执行后继的代码

            哈希算法的追求：在equals()不相等时，hashCode也不想等 - 这样能够提升👆面代码的执行效率
                1 使用自定义的对象作为map的key时，需要重写 hashCode() 与 equals()方法
                2 使用自定义的对象 作为set的元素时，也需要重写 hashCode() 与 equals()方法 - 这两个方法会被用来对象的判等
         */
        map.get("henry");
    }
}
