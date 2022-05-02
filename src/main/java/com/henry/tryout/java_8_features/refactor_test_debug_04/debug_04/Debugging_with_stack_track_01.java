package com.henry.tryout.java_8_features.refactor_test_debug_04.debug_04;

import com.henry.tryout.java_8_features.refactor_test_debug_04.test_lambda_expression_03.Point;

import java.util.Arrays;
import java.util.List;

public class Debugging_with_stack_track_01 {
    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);

        // 由于points中存在一个null元素，所以这里会抛出异常
        // 特征： 由于lambda表达式是没有名字的，所以得到的 日志本身不是很有说明性
        /*
            points.stream()
                .map(Point::getX)
                .forEach(System.out::println);
         */


        List<Integer> numbers = Arrays.asList(1, 2, 3);

        // 特征：如果所使用的方法引用 来自于当前类，那么：方法的名称就会在报错日志(栈跟踪)中显示出来
        numbers.stream()
                .map(Debugging_with_stack_track_01::divideByZero)
                .forEach(System.out::println);

    }

    private static int divideByZero(int n) {
        return n / 0;
    }
}
/*
排查异常的手段：
    先定位到 什么地方引发的异常；
    再找到异常发生的具体原因。
 */
