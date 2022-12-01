package com.henry.tryout.kotlin_basic

// 条件控制 - {if/else, when}
/*
    if的用法：
    #1 用于比较的关键字
    #2 直接用在return中；
 */
fun maxInt(param1: Int, param2: Int): Int {
    var value = 0
    if (param1 > param2) {
        value = param1
    } else {
        value = param2
    }
    return param2
}

fun maxInt2(param1: Int, param2: Int): Int {
    return if(param1 > param2) {param1} else {param2}
}

// 名字 -> 分数
// 手段1：使用if语句
fun getScore(name: String) = if (name == "Tom") {
    86
} else if (name == "Jim") {
    77
} else if (name == "Jack") {
    100
} else {
    0
}

// 手段2：使用when 实现流程控制
// 特征：when能够匹配任何类型的值
fun getScore2(name: String) = when(name) {
    "Tom" -> 86
    "Jim" -> 77
    "Jack" -> 100
    else -> 0
}

// 使用when 来 实现类型判断
fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number is not supported")
    }
}

fun main() {
    val a = 10
    val b = 20
    val max = maxInt(a, b)
    val max2 = maxInt2(a, b)
    println("整数中的较大者为： " + max + " aka " + max2)

    val tomScore = getScore2("Tom")
    println("Tom's score is: " + tomScore)

    checkNumber(num = 10.0)
}