package com.henry.tryout.kotlin_basic

import java.lang.Integer.max

fun biggerNumber(param1: Int, param2: Int): Int {
    return max(param1, param2)
}

// 实现2: 语法糖
fun biggerNumber2(param1: Int, param2: Int): Int = max(param1, param2)

// 实现3：省掉返回值类型
fun biggerNumber3(param1: Int, param2: Int) = max(param1, param2)

fun main() {
    val a = 10
    val b = 20
    val biggerNum = biggerNumber(a, b)
    println("bigger number is: " + biggerNum)

    val bigger2 = biggerNumber2(a, b)
    val bigger3 = biggerNumber3(a, b)
    println("bigger number2: " + bigger2 + ", bigger number3: " + bigger3)

}