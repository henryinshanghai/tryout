package com.henry.tryout.java_8_features.refactor_test_debug_04.test_lambda_expression_03;

import com.google.common.base.Objects;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // 针对此方法 进行单元测试 - testMoveRightBy
    // public的方法比较容易测试 - 但lambda表达式没有名字，不容易测试
    public Point moveRightBy(int x) {
        return new Point(this.x + x, this.y);
    }

    // 对lambda表达式进行测试 - 手段：借助某个字段来访问到lambda函数
    // 特征：Lambda表达式会生成 函数接口的一个实例。
    public final static Comparator<Point> compareByXAndThenY
            = Comparator.comparing(Point::getX).thenComparing(Point::getY);

    // 通过使用lambda表达式的方法 来 对lambda进行测试
    // lambda表达式的初衷是封装逻辑后，给其他方法使用 - 你不应该把它声明为public
    public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
        return points.stream()
                .map(point -> new Point(point.getX() + x, point.getY()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y);
    }
}
