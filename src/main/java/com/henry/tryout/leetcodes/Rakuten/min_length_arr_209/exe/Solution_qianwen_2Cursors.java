package com.henry.tryout.leetcodes.Rakuten.min_length_arr_209.exe;

public class Solution_qianwen_2Cursors {
    public int minSubArrayLen(int targetSum, int[] numArr) {
        int numAmount = numArr.length;

        // 准备 滑动窗口的左边界
        int subArrLeftBar = 0;
        // 当前窗口元素的加和值   手段：用long 来 防止溢出（可选）
        long currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int currentNumCursor = 0; currentNumCursor < numAmount; currentNumCursor++) {
            int subArrRightBar = currentNumCursor;
            // 把 当前数组元素 添加到 子序列中
            currentSum += numArr[subArrRightBar];

            // 尝试 收缩左边界(可能多次) 来 得到 以当前右边界作为结束元素的、最短的、满足条件的 子数组
            while (currentSum >= targetSum) {
                /* 尝试使用 当前’合法子数组‘的 长度 来 更新minLength */
                minLength = Math.min(minLength, subArrRightBar - subArrLeftBar + 1);

                /* 收缩窗口的左边界 */
                // 从sum中 移除 左边界元素
                currentSum -= numArr[subArrLeftBar];
                // 把 左边界 向右移动一个位置
                subArrLeftBar++;
            } /* 退出while循环时，子数组的subArrCurrentSum 已经 小于target，可以 继续追加元素了 */
        }

        // 如果 minLength的值 在for循环后，没有发生变化，说明 压根没有找到 满足条件的子数组，
        // 则：返回0；否则，返回 算法计算出的minLength
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
