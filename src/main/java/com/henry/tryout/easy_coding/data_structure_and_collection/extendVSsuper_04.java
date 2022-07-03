package com.henry.tryout.easy_coding.data_structure_and_collection;

import java.util.ArrayList;
import java.util.List;

public class extendVSsuper_04 {
    public static void main(String[] args) {
        // <? extends T> 表示能够接受 T与T的子类型。把?看作未知数x即可
        List<Animal> animal = new ArrayList<>();
        List<Cat> cat = new ArrayList<Cat>();
        List<Garfield> garfields = new ArrayList<>();

        animal.add(new Animal());
        cat.add(new Cat());
        garfields.add(new Garfield());

        // 测试 赋值操作
//        List<? extends Cat> extendsCat = animal; // 只能接受 Cat或者Cat子类的集合
        List<? super Cat> superCatFromAnimal = animal; // 接受 Cat或者Cat超类的集合

        List<? extends Cat> extendsCatFromCat = cat;
        List<? super Cat> superCatFromCat = cat;

        List<? extends Cat> extendsCatFromGarFiled = garfields;
//        List<? super Cat> superCatFromGarField = garfields; 只能赋值 Cat或者Cat父类的集合

        // 第三段
//        extendsCatFromCat.add(new Animal());
//        extendsCatFromCat.add(new Cat());
//        extendsCatFromCat.add(new Garfield());

//        superCatFromCat.add(new Animal());
        superCatFromCat.add(new Cat());
        superCatFromCat.add(new Garfield());

        // todo TBD

    }
}
