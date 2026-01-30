package com.henry.tryout.leetcodes.Huawei.phase3.two_cursors.threeSum_15.exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_qianwen_2Cursors {
    public List<List<Integer>> threeSum(int[] numArr) {
        // 准备一个列表的列表 用于收集 所有有效的三元组
        List<List<Integer>> allValid3NumsList = new ArrayList<>();

        // 处理 特殊的入参
        if (numArr == null || numArr.length < 3) {
            return allValid3NumsList;
        }

        // 步骤1：对 所有数字 进行排序
        Arrays.sort(numArr);
        int numAmount = numArr.length;

        // 步骤2：遍历所有位置，并 尝试从 当前位置 构建 满足条件的三元组
        for (int currentNumSpot = 0; currentNumSpot < numAmount - 2; currentNumSpot++) {
            // 剪枝：如果当前数字>0，说明 无法从它开始 构建 满足条件的三元组，
            // 并且 后继位置 都不可能再构建出 满足条件的三元组（因为 当前数字作为最小数 > 0，三元组之和不可能为0）
            if (numArr[currentNumSpot] > 0) {
                // 则：跳出循环
                break;
            }

            // 第一层去重：跳过 重复的起始值
            // 如果 当前位置上的数字 与 其前一个位置上的数字 相等，说明 它是一个重复元素，则：
            if (currentNumSpot > 0 &&
                    numArr[currentNumSpot] == numArr[currentNumSpot - 1]) {
                // 跳过 此重复元素
                continue;
            }

            // 初始化 两个游标指针的位置
            // 左指针 初始指向 当前位置的下一个位置
            int leftCursor = currentNumSpot + 1;
            // 右指针 初始指向 数字序列的末尾位置
            int rightCursor = numAmount - 1;

            /* 以 当前位置 作为起点，开始构建 所有 能够满足题意的三元组（用while） */
            while (leftCursor < rightCursor) {
                // 计算 当前位置、左数字指针、右数字指针 3个位置上的元素的和
                int current3Sum = numArr[currentNumSpot] + numArr[leftCursor] + numArr[rightCursor];

                // 如果 和为0，说明 找到了一个 满足条件的三元组
                if (current3Sum == 0) {
                    // 则：把 满足条件的三元组（转化为list后） 记录到 列表中
                    allValid3NumsList.add(Arrays.asList(numArr[currentNumSpot], numArr[leftCursor], numArr[rightCursor]));

                    // 第二层去重：跳过重复的 leftCursor 和 rightCursor
                    while (leftCursor < rightCursor &&
                            numArr[leftCursor] == numArr[leftCursor + 1]) {
                        leftCursor++;
                    }
                    while (leftCursor < rightCursor &&
                            numArr[rightCursor] == numArr[rightCursor - 1]) {
                        rightCursor--;
                    }

                    /* 收缩指针范围 来 查找下一个 满足条件的三元组 */
                    // 把 游标指针 移动到下一个位置
                    leftCursor++;
                    rightCursor--;
                } else if (current3Sum < 0) {
                    leftCursor++; // 需要 更大的和
                } else {
                    rightCursor--; // 需要 更小的和
                }
            }
        }

        // 返回 所有满足条件的三元组列表
        return allValid3NumsList;
    }
}
