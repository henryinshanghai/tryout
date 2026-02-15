package com.henry.tryout.leetcodes.Rakuten.most_water_container_11.exe;

// 可读性差，但是 代码表现 可能会更好
public class Solution_Krahets_ternaryOperator {
    public int maxArea(int[] spotToItsHeightArr) {
        int leftBarCursor = 0;
        int rightBarCursor = spotToItsHeightArr.length - 1;
        int maxWater = 0;

        // 每次循环，都改变容器中的短板(容器形状)，计算 容器的最大面积
        while (leftBarCursor < rightBarCursor) {
            // 使用 三目运算符 来 简化代码（炫技之嫌）
            maxWater = spotToItsHeightArr[leftBarCursor] < spotToItsHeightArr[rightBarCursor] ?
                    Math.max(maxWater, (rightBarCursor - leftBarCursor) * spotToItsHeightArr[leftBarCursor++]) : // 先计算容量，再向内移动短板
                    Math.max(maxWater, (rightBarCursor - leftBarCursor) * spotToItsHeightArr[rightBarCursor--]); // 同上
        }

        return maxWater;
    }
}
