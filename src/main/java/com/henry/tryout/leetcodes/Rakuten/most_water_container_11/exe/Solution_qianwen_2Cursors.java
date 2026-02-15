package com.henry.tryout.leetcodes.Rakuten.most_water_container_11.exe;

/*
在每个状态下，无论长板或短板向中间收窄一格，都会导致水槽 底边宽度 −1 变短：
    ① 若向内 移动短板 ，水槽的短板 min(h[i],h[j]) 可能变大，因此下个水槽的面积 可能增大 。
    ② 若向内 移动长板 ，水槽的短板 min(h[i],h[j]) 不变或变小，因此下个水槽的面积 一定变小 。
 */
// ① 移动 当前容器的短板，不断改变容器的形状。
// ② 使用 当前容器的面积 来 更新 最大面积
public class Solution_qianwen_2Cursors {
    public int maxArea(int[] spotToItsHeightArr) {
        int leftBarCursor = 0;
        int rightBarCursor = spotToItsHeightArr.length - 1;
        int maxWater = 0;

        // 在 动态移动边界 的过程中，找到 最大面积
        while (leftBarCursor < rightBarCursor) {
            // 当前容器的宽度和有效高度
            int width = rightBarCursor - leftBarCursor;
            int effectiveHeight = Math.min(spotToItsHeightArr[leftBarCursor], spotToItsHeightArr[rightBarCursor]);
            int currentWater = effectiveHeight * width;

            // （改变容器的形状后）尝试更新 容器的最大容积
            maxWater = Math.max(maxWater, currentWater);

            // 改变容器的形状 以 重新计算其容积
            // 手段：向内移动 较矮的那一边（贪心策略）
            if (spotToItsHeightArr[leftBarCursor] < spotToItsHeightArr[rightBarCursor]) {
                leftBarCursor++;
            } else {
                rightBarCursor--;
            }
        } /* 每次循环后 改变了容器的形状，也更新了 水的最大体积 */

        return maxWater;
    }
}
