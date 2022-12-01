package com.henry.tryout.kotlin_basic.oop.`object`

/*
验证：
    #1 使用var 来 声明可变变量
    #2 使用fun 来 声明方法
    #3 创建实例对象时，不需要new关键字
    #4 使用open 来 声明类可以被继承
 */
open class Person(name: String, age: Int ) {
    var name = ""
    var age = 0
    fun eat() {
        println(name + " is eating. He is " + age + " year old")
    }
}