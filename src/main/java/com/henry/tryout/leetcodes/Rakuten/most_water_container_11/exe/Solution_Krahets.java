package com.henry.tryout.leetcodes.Rakuten.most_water_container_11.exe;

// 可读性差，但是 代码表现 可能会更好
public class Solution_Krahets {
    public int maxArea(int[] spotToItsHeightArr) {
        int leftBar = 0,
                rightBar = spotToItsHeightArr.length - 1,
                maxWater = 0;

        // 每次循环，都改变容器中的短板(容器形状)，计算 容器的最大面积
        while (leftBar < rightBar) {
            maxWater = spotToItsHeightArr[leftBar] < spotToItsHeightArr[rightBar] ?
                    Math.max(maxWater, (rightBar - leftBar) * spotToItsHeightArr[leftBar++]) : // 先计算容量，再向内移动短板
                    Math.max(maxWater, (rightBar - leftBar) * spotToItsHeightArr[rightBar--]); // 同上
        }

        return maxWater;
    }
}
