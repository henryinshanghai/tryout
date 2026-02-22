package com.henry.tryout.leetcodes.Huawei.phase1.array.longest_harmonious_subsequence_594.exe;

import java.util.Arrays;

public class Sloution_gongshui_2cursors {
    public int findLHS(int[] numArr) {
        Arrays.sort(numArr);
        int numAmount = numArr.length, maxSeqLength = 0;

        int windowLeftCursor = 0;
        for (int currentNumCursor = 0; currentNumCursor < numAmount; currentNumCursor++) {
            /* 扩展窗口的右边界指针 */
            int windowRightCursor = currentNumCursor;

            /* （扩展右边界后）按需恢复窗口的合法性 */
            // 当 窗口中的右指针所指向元素 与 左指针所指向元素的 差值 大于1时，
            // 说明 当前窗口中的子数组 已经不是 和谐数组，
            while (windowLeftCursor < windowRightCursor &&
                    numArr[windowRightCursor] - numArr[windowLeftCursor] > 1) {
                // 则：收缩窗口
                // 手段：把 左指针 向后移动一个位置；
                windowLeftCursor++;
            }

            /* （窗口合法后）尝试更新 最长序列长度 */
            // 如果 右指针所指向的元素 与 左指针所指向的元素 的差值 等于1，
            // 说明 当前窗口的子数组 是一个和谐数组，
            if (numArr[windowRightCursor] - numArr[windowLeftCursor] == 1) {
                // 则：尝试 用当前数组的长度 来 更新最长和谐子序列的长度
                maxSeqLength = Math.max(maxSeqLength,
                        windowRightCursor - windowLeftCursor + 1);
            }
        }

        return maxSeqLength;
    }
}
