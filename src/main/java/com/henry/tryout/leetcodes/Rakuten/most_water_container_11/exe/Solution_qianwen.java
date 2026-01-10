package com.henry.tryout.leetcodes.Rakuten.most_water_container_11.exe;

/*
在每个状态下，无论长板或短板向中间收窄一格，都会导致水槽 底边宽度 −1 变短：
    ① 若向内 移动短板 ，水槽的短板 min(h[i],h[j]) 可能变大，因此下个水槽的面积 可能增大 。
    ② 若向内 移动长板 ，水槽的短板 min(h[i],h[j]) 不变或变小，因此下个水槽的面积 一定变小 。
 */
// ① 移动 当前容器的短板，不断改变容器的形状。
// ② 使用 当前容器的面积 来 更新 最大面积
public class Solution_qianwen {
    public int maxArea(int[] height) {
        int leftBar = 0;
        int rightBar = height.length - 1;
        int maxWater = 0;

        // 在 动态移动边界 的过程中，找到 最大面积
        while (leftBar < rightBar) {
            // 当前容器的宽度和有效高度
            int width = rightBar - leftBar;
            int effectiveHeight = Math.min(height[leftBar], height[rightBar]);
            int currentWater = effectiveHeight * width;

            maxWater = Math.max(maxWater, currentWater);

            // 移动 较短的一边（贪心策略），改变容器的形状 以 重新计算其容积
            if (height[leftBar] < height[rightBar]) {
                leftBar++;
            } else {
                rightBar--;
            }
        } /* 每次循环后 改变了容器的形状，也更新了 水的最大体积 */

        return maxWater;
    }
}
