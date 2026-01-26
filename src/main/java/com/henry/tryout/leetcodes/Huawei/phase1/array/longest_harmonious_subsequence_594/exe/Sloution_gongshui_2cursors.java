package com.henry.tryout.leetcodes.Huawei.phase1.array.longest_harmonious_subsequence_594.exe;

import java.util.Arrays;

public class Sloution_gongshui_2cursors {
    public int findLHS(int[] numArr) {
        Arrays.sort(numArr);
        int numAmount = numArr.length, maxSeqLength = 0;

        for (int leftCursor = 0, rightCursor = 0; rightCursor < numAmount; rightCursor++) {
            // 当 窗口中的右指针所指向元素 与 左指针所指向元素的 差值 大于1时，说明 当前窗口中的子数组 已经不是 和谐数组，则：
            while (leftCursor < rightCursor &&
                    numArr[rightCursor] - numArr[leftCursor] > 1) {
                // 把 左指针向后移动一个位置 来 收缩窗口
                leftCursor++;
            }
            // 如果 右指针所指向的元素 与 左指针所指向的元素 的差值 等于1，说明 当前窗口的子数组 是一个和谐数组，则：
            if (numArr[rightCursor] - numArr[leftCursor] == 1) {
                // 尝试 用当前数组的长度 来 更新最长和谐子序列的长度
                maxSeqLength = Math.max(maxSeqLength, rightCursor - leftCursor + 1);
            }
        }
        return maxSeqLength;
    }
}
