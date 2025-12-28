package com.henry.tryout.easy_coding.data_structure_and_collection_06.compare_items_06.equal_object_03;

import com.google.common.base.Objects;

import java.util.HashSet;
import java.util.Set;

// 目标：逻辑上表示 自定义对象的唯一性；
// 手段：自定义hashCode() + equals()方法
// 验证手段：向 set对象中 连续添加 多个逻辑上相等的自定义对象实例，查看set会不会 把 它们 视为 同一个对象
// 应用：判断 两个对象 是否“逻辑上相等”
public class EqualsObject {
    private int id;
    private String name;

    public EqualsObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) { // o 为 传入的待比较的对象
        // #1 如果 当前对象 与 传入的对象 指向 同一个地址，说明 它们是同一个对象，
        if (this == o) {
            // 则：返回true
            return true;
        }
        // #2 如果 传入的对象 为null, 或者 与 当前对象 不是 同一个类型 则：返回false
        if (o == null || getClass() != o.getClass()) return false;

        // #3 如果 对象的属性 都相同,则：认为对象是 逻辑上相等的
        EqualsObject that = (EqualsObject) o;
        return id == that.id && Objects.equal(name, that.name);
    }

    // 关键点：没有 同时重写 hashCode()方法
    @Override
    public int hashCode() {
        // 重写 hashCode()方法 - 不再是 返回对象地址
        // 返回一个 根据 自定义对象的属性值 计算得到的结果    手段：Objects.hashCode()方法   特征：属性值相同时, 返回的hashCode的值就会相同
        int hashResult = Objects.hashCode(id, name);
        System.out.println("hash的结算结果为： " + hashResult);
        return hashResult;
    }

    public static void main(String[] args) {
        Set<EqualsObject> hashSet = new HashSet<>();

        // a, b, c是同一个对象吗？
        // 答：取决于 hashCode() 有没有 被根据对象属性重写 - 如果有，会被视为同一个对象； 如果没有，则：视为不同对象
        EqualsObject a = new EqualsObject(1, "one");
        EqualsObject b = new EqualsObject(1, "one");
        EqualsObject c = new EqualsObject(1, "one");

        // 把对象a, b, c分别添加到set集合中
        hashSet.add(a);
        hashSet.add(b);
        hashSet.add(c);

        /*
            如果 没有覆写/注释掉 hashCode()方法，则：a,b,c 不会被视为 同一个对象 size = 3
            如果 覆写了 hashCode() 并 返回 与属性相关的id，则：a,b,c 会被视为 同一个对象 size=1
         */
        System.out.println(hashSet.size());
    }
}
