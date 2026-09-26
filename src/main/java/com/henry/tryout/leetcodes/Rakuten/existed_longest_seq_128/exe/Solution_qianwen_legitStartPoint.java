package com.henry.tryout.leetcodes.Rakuten.existed_longest_seq_128.exe;

import java.util.HashSet;
import java.util.Set;

// 这道题 考察的 不是 复杂数据结构，而是 对问题本质的洞察力————如何 避免 无效计算。
// 这个实现 在新的测试用例中 会超时，无法AC
public class Solution_qianwen_legitStartPoint {
    public int longestConsecutive(int[] nums) {
        // 把数组中的所有元素 都添加到一个set对象中
        // 用于 ① 快速判断 指定的元素 是否存在；② 去除重复元素；
        Set<Integer> numSet = new HashSet<>();
        for (int currentNum : nums) {
            numSet.add(currentNum);
        }

        int maxSeqLength = 0;

        // 🐖 原始数组中可能包含有 重复元素，所以 遍历nums不是个好主意
        for (int currentNum : numSet) {
            // 如果 元素(currentNum-1) 在nums中不存在，说明 currentNum 是一个 候选序列的起点，
            if (isALegitStartPoint(numSet, currentNum)) {
                /* 则：从它开始 对‘连续序列‘ 进行扩展 */

                // 定义一个指针变量 指向 当前元素(基于当前元素来扩展 连续序列)
                int numToExtend = currentNum;
                int currentConsecutiveSeqLength = 1;

                // 当 currentNum+1 在numSet中存在时，说明 可以 对’当前连续序列‘向右扩展，
                while (ableToExtend(numSet, numToExtend)) {
                    // 则：向右移动 当前元素指针
                    numToExtend++;
                    // 更新 ’当前连续序列‘的长度
                    currentConsecutiveSeqLength++;
                }

                // 使用 ’当前连续序列‘的长度 来 尝试更新 最长序列的长度
                maxSeqLength = Math.max(maxSeqLength, currentConsecutiveSeqLength);
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
