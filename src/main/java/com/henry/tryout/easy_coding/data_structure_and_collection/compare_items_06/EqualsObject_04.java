package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06;

import com.google.common.base.Objects;

import java.util.HashSet;
import java.util.Set;

public class EqualsObject_04 {
    private int id;
    private String name;

    public EqualsObject_04(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        // 如果指向同一个引用对象，则：返回true
        if (this == o) return true;
        // 如果为null, 或者并非同类 则：返回false
        if (o == null || getClass() != o.getClass()) return false;

        // 对传入参数进行强制类型转换，来使用 当前类型的属性
        EqualsObject_04 that = (EqualsObject_04) o;
        // 如果两个属性值相同，则：认为两个对象相同
        return id == that.id && Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }

    public static void main(String[] args) {
        Set<EqualsObject_04> hashSet = new HashSet<>();

        EqualsObject_04 a = new EqualsObject_04(1, "one");
        EqualsObject_04 b = new EqualsObject_04(1, "one");
        EqualsObject_04 c = new EqualsObject_04(1, "one");

        hashSet.add(a);
        hashSet.add(b);
        hashSet.add(c);

        // 如果没有覆写 hashCode()方法，则：a,b,c不会被视为同一个对象
        // 因为默认的 hashCode()方法 返回的是一个 跟对象地址相关的唯一值
        System.out.println(hashSet.size());

    }
}
