package com.henry.tryout.easy_coding.data_structure_and_collection_06.map_08.hashMap_03;

import java.util.HashMap;

// 验证：HashMap的扩容动作 会发生在 size > capacity * factor时
public class HashMapResize_03 {
    private static HashMap map = new HashMap();

    public static void main(String[] args) {
        // 扩容的阈值 = table.length * 0.75
        // 所以第一次扩容会发生在 添加第13个元素的时候 - 怎么验证发生了扩容呢？
        for (int i = 0; i < 13; i++) {
            map.put(new UserKey(), new EasyCoding());
        }
    }
}

// 自定义的Key类型
class UserKey {
    // 让所有的EntryKey都落在同一个 哈希桶中 - 手段：hashCode()方法返回 固定值
    public int hashCode() {
        return 1;
    }

    // 在hash值相同的情况下，为了使new出的对象逻辑上不相等。- 手段：equals()方法返回false
    // 用法：e.hash == hash && ((k = e.key) == key || key.equals(k)) 为 false
    public boolean equals(Object obj) {
        return false;
    }
}

class EasyCoding {

}
