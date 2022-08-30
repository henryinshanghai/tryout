package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.equal_object_03;

import com.google.common.base.Objects;

import java.util.HashSet;
import java.util.Set;

public class EqualsObject {
    private int id;
    private String name;

    public EqualsObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) { // o为传入的用于比较的对象
        // #1 如果当前对象 与 传入的对象 指向同一个地址，则：返回true
        if (this == o) return true;
        // #2 如果传入的对象为null, 或者与当前对象不是同一个类型 则：返回false
        if (o == null || getClass() != o.getClass()) return false;

        // #3 如果对象的属性相同,则：认为对象是相同的
        EqualsObject that = (EqualsObject) o;
        return id == that.id && Objects.equal(name, that.name);
    }

    // 关键点：没有同时重写 hashCode()方法
    @Override
    public int hashCode() {
        // 计算当前对象的 hashCode值 - 手段：Objects.hashCode()方法
        return Objects.hashCode(id, name);
    }

    public static void main(String[] args) {
        Set<EqualsObject> hashSet = new HashSet<>();

        // a, b, c是同一个对象吗？ 答：取决于 hashCode()有没有被根据对象属性重写 - 如果有，视为同一个对象； 如果没有，则：视为不同对象
        EqualsObject a = new EqualsObject(1, "one");
        EqualsObject b = new EqualsObject(1, "one");
        EqualsObject c = new EqualsObject(1, "one");

        hashSet.add(a);
        hashSet.add(b);
        hashSet.add(c);

        /*
            如果没有覆写/注释掉 hashCode()方法，则：a,b,c不会被视为同一个对象 size = 3
            原理：默认的 hashCode()方法 返回的是一个 跟对象地址相关的唯一值

            如果覆写了 hashCode()，则：a,b,c会被视为同一个对象 size=1
         */
        System.out.println(hashSet.size());

    }
}
