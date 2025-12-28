package com.henry.tryout.easy_coding.object_orientation_02.method_04.data_type.primary_data_type;

// 验证：
// #1 计算 某个类的实例对象所占用的空间 时，只需要累加 其非静态成员变量所占用的空间即可
// #2 基本类型的成员变量 所占用的空间大小 是固定的(boolean一个字节、int4个字节)
// #3 引用类型的成员变量 所占用的空间大小 为4字节；其实例对象 所占用的空间 不计入 当前实例对象中
// #4 对象头 最少占用空间 为12字节
// #5 当前类的 所有可见的父类成员变量 所占用的空间，也需要计入
public class RefObjDemo {
    /* 对象头 最少占用空间：12字节 */

    /* 成员变量 所占用的空间 */
    byte b1;
    byte b2;
    byte b3;
    byte b4; // 每个byte 占用1个字节的空间 1*4=4

    Object object1;
    Object object2;
    Object object3;
    Object object4;
    Object object5; // 每个引用变量 占用4个字节的空间 4*5=20

    // 🐖 RefObjOther实例对象 所占用的空间 并不计算在 本对象中，只需要计入 引用变量所占的空间即可
    RefObjOther o1 = new RefObjOther();
    RefObjOther o2 = new RefObjOther(); // 引用变量 占4个字节的空间 4*2=8

    /* 综上，当前类的实例对象占用：12+4+20+8=44字节。向上取8的倍数 为48个字节 */
}

class RefObjOther {
    /* 对象头 至少占用 12字节 */
    // 对于 引用类型的成员变量，只需要计入 引用变量 所占的空间即可(4字节)
    double[] d = new double[1000];

    /* 综上，当前类的实例对象 会占用：12+4=16字节 */
}
