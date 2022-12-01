package com.henry.tryout.kotlin_basic

//for-in循环
fun loopThroughAscendingRange() {
    // 使用一个变量 来 表示一个范围
    val range = 0..10
    for(i in range) {
        println(i)
    }

    // 表示一个边界为开的范围 - until
    // 步距为2 - {0， 2， 4， 6， 8}
    for (i in 0 until 10 step 2) {
        println(i)
    }
}

fun loopThroughDiscendingRange() {
    // 描述一个降序的区间 - downTo
    for (i in 20 downTo 10 step 2) {
        println(i)
    }
}

fun main() {
    loopThroughAscendingRange()

    loopThroughDiscendingRange()
}
