package com.henry.tryout.kotlin_basic.oop.`object`

// 验证1：在Kotlin中，如果想要某个类是可被继承的，则：开发者必须要显式声明
// 原理：如果类可以随意地被继承(变量可以被修改)，往往就可能出现问题 - 可变性引发的问题
// 使一个类有 被继承的能力 - 手段：open关键字
// 声明 Student 继承自 Person - 语法： <冒号>:<被继承类的构造方法>
// 验证2：类的主构造函数写法 - 直接写在类名的后面(可以省略)👇
// 扩展主构造函数 - 包含更多的参数；   特征：新增的与父类同名的属性，不能再使用val/var修饰（因为被修饰的参数会自动成为类的字段）
open class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age){
//    var sno = ""
//    var grade = 0

    // 验证3：一般把主构造函数的逻辑 写在init结构体中
    init {
        println("sno is: " + sno)
        println("grade is " + grade)
    }

    // 新增一个次构造器 - 继承自 主构造器
    constructor(name: String, age: Int) : this("", 0, name, age) {

    }

    // 再新增一个次构造器 - 继承自上一个次构造器
    constructor() : this("", 0) {

    }

}

