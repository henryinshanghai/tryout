package com.henry.tryout.lambda_test;

import com.henry.tryout.java_8_features.refactor_test_debug_04.test_lambda_expression_03.Point;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class Lambda_Expression_Test {

    @Test
    public void testMoveRightBy() {
        Point p1 = new Point(5, 5);
        Point p2 = p1.moveRightBy(10);

        Assert.assertEquals(15, p2.getX());
        Assert.assertEquals(5, p2.getY());
    }

    // 通过字段来对lambda进行测试
    @Test
    public void testComparingTwoPoints() {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);

        int result = Point.compareByXAndThenY.compare(p1, p2);
        Assert.assertEquals(-1, result);
    }

    // 对使用lambda表达式的方法 进行测试
    @Test
    public void testMoveAllPointsRightBy() {
        List<Point> points =
                Arrays.asList(new Point(5, 5), new Point(10, 5));
        List<Point> expectedPoints =
                Arrays.asList(new Point(15, 5), new Point(20, 5));

        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);

        Assert.assertEquals(newPoints, expectedPoints);
    }
}
