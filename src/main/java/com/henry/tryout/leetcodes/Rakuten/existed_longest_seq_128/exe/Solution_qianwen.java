package com.henry.tryout.leetcodes.Rakuten.existed_longest_seq_128.exe;

import java.util.HashSet;
import java.util.Set;

// 这道题 考察的 不是 复杂数据结构，而是 对问题本质的洞察力————如何避免无效计算。
// 这个实现 在新的测试用例中 会超时，无法AC
public class Solution_qianwen {
    public int longestConsecutive(int[] nums) {
        // 把数组中的所有元素 都添加到一个set对象中，用于 快速判断 指定的元素 是否存在
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int maxSeqLength = 0;

        // 🐖 原始数组中可能包含有 重复元素，所以 遍历nums不是个好主意
        for (int currentNum : nums) {
            // 如果 x-1 在nums中不存在，说明 x是一个 候选序列的起点，则：从它开始 对序列进行扩展
            if (isALegitStartPoint(numSet, currentNum)) {
                // 定义一个指针变量 指向 当前元素
                int numToExtend = currentNum;
                int currentLength = 1;

                // 当 x+1 在nums中存在时，说明 可以 对连续序列向右扩展，则：
                while (ableToExtend(numSet, numToExtend)) {
                    // 向右移动 当前元素指针
                    numToExtend++;
                    currentLength++;
                }

                // 使用 当前候选序列的长度 来 尝试更新 最长序列的长度
                maxSeqLength = Math.max(maxSeqLength, currentLength);
            }
        }

        return maxSeqLength;
    }

    private boolean ableToExtend(Set<Integer> numSet, int cursorToExtendSeq) {
        return numSet.contains(cursorToExtendSeq + 1);
    }

    private boolean isALegitStartPoint(Set<Integer> numSet, int num) {
        return !numSet.contains(num - 1);
    }
}
