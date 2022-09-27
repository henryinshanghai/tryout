package com.henry.tryout.easy_coding.data_structure_and_collection_06.collection_and_generic_05.multiple_type_under_constraints_03;

import java.util.ArrayList;
import java.util.List;

// 验证：在集合中，能够限定 “元素类型可以为多个不同的类型” - 但是要遵守不同的约束
// 手段1：? extends T 手段2： ? super T
public class multipleTypeWithGenericRestrict {
    public static void main(String[] args) {
        // 目标：希望集合中，能够添加多种类型(这些类型都受到某一个泛型的约束)
        List<Animal> animal = new ArrayList<>();
        List<Cat> cat = new ArrayList<Cat>();
        List<Garfield> garfields = new ArrayList<Garfield>();

        animal.add(new Animal());
        cat.add(new Cat());
        garfields.add(new Garfield());

        /*
            手段1：声明<? extends T>的泛型
            特征：
                1 允许添加 T类型的实例 与 所有继承自T类型的子类的实例 进入集合中；
                2 取出item时，item的类型会被向上强转为 T(因为编译器只记录了 T类型).
            应用：extends T 主要用于 get元素

            手段2：声明<? super T>的泛型
            特征：允许添加 T类型的实例 与 所有T类型的父类的实例。
            应用：super T 主要用于 put元素
         */

        /* #1 测试 赋值操作 👇 */
        // 把 List<Animal> 类型的变量 赋值到 List<? extends Cat>类型的变量上
//        List<? extends Cat> extendsCatFromCat = animal; // 编译报错， 因为 Animal 不是继承自Cat的
        // 把 List<Animal> 类型的变量 赋值到 List<? super Cat>类型的变量上
        List<? super Cat> superCatFromAnimal = animal; // 编译成功， 因为 Animal super from Cat

        // 把 List<Cat>类型的变量 赋值到 List<? extends Cat>类型的变量上
        List<? extends Cat> extendsCatFromCat = cat; // 编译通过 因为Cat 是继承自Cat的
        // 把 List<Cat>类型的变量 赋值到 List<? super Cat>类型的变量上
        List<? super Cat> superCatFromCat = cat; // 编译通过 因为Cat 是super自Cat的

        // 把 List<Garfield>类型的变量 赋值到 List<? extends Cat>类型的变量上
        List<? extends Cat> extendsCatFromGarFiled = garfields; // 编译通过 因为 Garfield 是继承自Cat的
        // 把 List<Garfield>类型的变量 赋值到 List<? super Cat>类型的变量上
//        List<? super Cat> superCatFromGarField = garfields;  // 编译报错, 因为 Garfield 不是super from Cat的

        /* #2 测试add()方法 👇 */
        // 验证：泛型声明为<? extends Cat>的 泛型集合 不支持add()操作 (为啥？add都不支持还有个卵用？)
//        extendsCatFromCat.add(new Animal());
//        extendsCatFromCat.add(new Cat());
//        extendsCatFromCat.add(new Garfield());

        // 验证：泛型声明为<? super Cat>的 泛型集合 能够支持add()操作 - 但只能添加 Cat 或者 Cat的子类(为啥是子类呢？ 这和声明的泛型不是不一致吗？ 还是说声明的泛型 只约束赋值行为，而不约束添加操作)
//        superCatFromCat.add(new Animal());
        superCatFromCat.add(new Cat());
        superCatFromCat.add(new Garfield());

        /* #3 测试get()方法 👇 */
        // 从 List<? super Cat>类型的集合变量中取值 - 取出的值是 Object类型
        Object object = superCatFromCat.get(0);
        // 从 List<? extends Cat>类型的集合变量中取值 - 取出的值是 Cat类型
        Cat cat1 = extendsCatFromCat.get(0);
        // 从 List<? extends Cat>类型的集合变量中取值 - 取出的值是 Cat类型； 原因：存入的对象的类型会被擦除掉，存储时就只剩下Cat类型
//        Garfield cat2 = extendsCatFromGarFiled.get(0);

    }
}
/*
启示：
    <? extends T>适用于 get元素的场合 - 它能够: #1 获取到T类型的元素; #2 但不支持任何的add()操作
    <? super T>适用于 存储元素的场合 - 它能够 add T类型 与 <T类型子类>的元素，但是get item时 只能够得到Object类型的item
    List<? super T>的泛型声明 与 所能够添加元素的类型(T与T的子类型) 为什么是矛盾的？
        泛型约束： 约束变量绑定时所能接受的变量类型;
        add操作： 与泛型约束没有直接短息，只能添加 T或者T的子类。
 */
