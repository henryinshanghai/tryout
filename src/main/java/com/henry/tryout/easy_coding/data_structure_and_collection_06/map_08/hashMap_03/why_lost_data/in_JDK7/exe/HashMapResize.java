package com.henry.tryout.easy_coding.data_structure_and_collection_06.map_08.hashMap_03.why_lost_data.in_JDK7.exe;

import java.util.HashMap;

// 验证：HashMap的第一次扩容动作 会发生在 size > capacity * factor时
public class HashMapResize {
    private static HashMap map = new HashMap();

    public static void main(String[] args) {
        // 扩容的阈值 = table.length * 0.75
        // 所以 第一次扩容 会发生在 添加第13个元素的时候 - 怎么验证 发生了扩容呢？
        for (int i = 0; i < 13; i++) {
            map.put(new UserKey(), new EasyCoding());
        }
    }
}

// 自定义的Key类型
class UserKey {
    // 让 所有的EntryKey 都 落在同一个哈希桶中 - 手段：hashCode()方法 返回 固定值
    public int hashCode() {
        return 1;
    }

    // 在 hash值相同的情况 下，为了使 new出的对象 逻辑上不相等。
    // 手段：equals()方法返回false
    // 用法：e.hash == hash && ((k = e.key) == key || key.equals(k)) 为 false
    public boolean equals(Object obj) {
        return false;
    }
}

class EasyCoding {

}
