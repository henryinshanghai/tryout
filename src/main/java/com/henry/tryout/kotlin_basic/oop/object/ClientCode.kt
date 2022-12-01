package com.henry.tryout.kotlin_basic.oop.`object`

fun main() {
    // 创建对象不需要使用new关键字
    val p = Person("Jack", 19)
    p.eat()

    // 使用构造方法 来 创建对象
    // 特征：这种写法的好处是 - 初始化Student类时，init结构体会被执行
    // 验证：在初始化子类时，一定会先调用父类的构造函数
    // 特征：具体调用哪一个构造方法，取决于 父类构造方法的参数列表
    val student = Student("a123", 5)

    val student2 = Student()

    val student3 = Student("512000", 5, "Jack", 19)

}