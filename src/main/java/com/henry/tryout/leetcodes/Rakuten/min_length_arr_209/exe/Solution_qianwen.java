package com.henry.tryout.leetcodes.Rakuten.min_length_arr_209.exe;

public class Solution_qianwen {
    public int minSubArrayLen(int targetSum, int[] numArr) {
        int numAmount = numArr.length;
        int subArrLeftBar = 0;
        long currentSum = 0; // 用 long 防止溢出（可选）
        int minLength = Integer.MAX_VALUE;

        for (int subArrRightBar = 0; subArrRightBar < numAmount; subArrRightBar++) {
            // 把 当前数组元素 添加到 子序列中
            currentSum += numArr[subArrRightBar];

            // 尝试收缩左边界(可能多次) 来 得到 以当前右边界作为结束元素的、最短的、满足条件的子数组
            while (currentSum >= targetSum) {
                // 计算 当前子数组的 数组长度，尝试 更新minLength
                minLength = Math.min(minLength, subArrRightBar - subArrLeftBar + 1);

                // 从sum中 移除 左边界元素
                currentSum -= numArr[subArrLeftBar];
                // 把 左边界 向右移动一个位置
                subArrLeftBar++;
            } /* 退出while循环时，子数组的subArrCurrentSum 已经 小于target，可以继续追加元素了 */
        }

        // 如果 minLength的值 在for循环后，没有发生变化，说明 压根没有找到 满足条件的子数组，
        // 则：返回0；否则，返回 算法计算出的minLength
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
