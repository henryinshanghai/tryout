package com.henry.tryout.easy_coding.data_structure_and_collection.collection_and_generic_05;

import java.util.ArrayList;
import java.util.List;

public class multipleTypeWithGenericRestrict_04 {
    public static void main(String[] args) {
        // 目标：集合中可以添加多种类型，但是这些类型都受到某一个泛型的约束
        // 手段： <? extends T>, <? super T>
        // <? extends T> 支持 T类型与所有继承自T类型的子类。取出item时，向上强转为 T
        // 特征：extends T主要用于 get元素
        // <? super T> 支持 T类型与所有T类型的父类。
        // 特征： super T主要用于 put元素

        // <? extends T> 表示能够接受 T与T的子类型。把?看作未知数x即可
        List<Animal> animal = new ArrayList<>();
        List<Cat> cat = new ArrayList<Cat>();
        List<Garfield> garfields = new ArrayList<Garfield>();

        animal.add(new Animal());
        cat.add(new Cat());
        garfields.add(new Garfield());

        // #2 测试 赋值操作
//        List<? extends Cat> extendsCat = animal; // 只能接受 Cat或者Cat子类的集合
        List<? super Cat> superCatFromAnimal = animal; // 接受 Cat或者Cat超类的集合

        List<? extends Cat> extendsCatFromCat = cat;
        List<? super Cat> superCatFromCat = cat;

        List<? extends Cat> extendsCatFromGarFiled = garfields;
//        List<? super Cat> superCatFromGarField = garfields; 只能赋值 Cat或者Cat父类的集合

        // #3 测试add()方法 - ? extends Cat的泛型集合不支持 add()操作
//        extendsCatFromCat.add(new Animal());
//        extendsCatFromCat.add(new Cat());
//        extendsCatFromCat.add(new Garfield());

        // ? super Cat的泛型集合 只能添加 Cat 或者 Cat的子类？ 为啥是子类呢？
//        superCatFromCat.add(new Animal());
        superCatFromCat.add(new Cat());
        superCatFromCat.add(new Garfield());

        // #4 测试Get方法
        // super Cat的集合取出元素时，泛型会丢失 - 只会返回Object类型的对象
        Object object = superCatFromCat.get(0);
        // extends Cat集合取出元素时，能够取出 Cat类型的item
        Cat cat1 = extendsCatFromCat.get(0);
        // 由于类型擦除，虽然存入时使用的是 Garfield类型，但是取出时，就只能得到Cat类型
//        Garfield cat2 = extendsCatFromGarFiled.get(0);

    }
}
/*
启示：
    <? extends T>适用于获取元素的场合 - 它能够得到T类型的元素，但不支持任何的add()操作
    <? super T>使用于 存储元素的场合 - 它能够添加T类型 与 T类型子类的元素，但是获取item时只能够得到Object类型的item
 */
