package com.henry.tryout.leetcodes.Rakuten.permutation_sequence_60.exe;

import java.util.ArrayList;
import java.util.List;

/*
① groupSize = (remaining−1)! 告诉我们每组有多少个排列
② groupIndex = k / groupSize 告诉我们选哪个数字
③ k % groupSize 给出子问题的新 k
④ availableDigits 动态维护剩余数字的字典序，确保 get(i) 总是第 i 小的可用数字
 */
public class Solution_qianwen {
    public String getPermutation(int totalDigitAmount, int targetRank) {
        // Step 1: 预计算 阶乘数组 fact，其中 fact[i] = i!
        // fact[i] 表示 i!，用于 后续计算 “每组排列的数量”
        int[] nToItsPermutationAmount = new int[totalDigitAmount];
        nToItsPermutationAmount[0] = 1; // 0! = 1

        for (int currentN = 1; currentN < totalDigitAmount; currentN++) {
            nToItsPermutationAmount[currentN] = nToItsPermutationAmount[currentN - 1] * currentN;
        }

        // Step 2: 初始化 可用数字列表 [1, 2, ..., n]
        // 这个列表 动态维护 “尚未被选中的数字”，按 升序 排列（保证字典序）
        List<Integer> availableDigitSeqInAsc = new ArrayList<>();
        for (int currentDigit = 1; currentDigit <= totalDigitAmount; currentDigit++) {
            availableDigitSeqInAsc.add(currentDigit);
        }

        // Step 3: 将 k(第多少个) 转换为 0-based 索引（便于 整除 和 取余 计算）
        int zeroBasedRank = targetRank - 1;

        // Step 4: 逐位构造 结果字符串
        StringBuilder targetPermutation = new StringBuilder();

        // 从 当前剩余数字中 选择 正确的字符
        for (int remainingDigitAmount = totalDigitAmount; remainingDigitAmount > 0; remainingDigitAmount--) {
            // 当前每个 “首数字组”的大小 = (remainingDigitAmount - 1)!
            int subPermutationAmountPerGroup = nToItsPermutationAmount[remainingDigitAmount - 1];

            // 计算 第zeroBasedRank个排列 落在哪个组（即应选择 availableDigitSeqInAsc 中的第几个数字）
            int fallInGroupIndex = zeroBasedRank / subPermutationAmountPerGroup;

            // 从 可用数字列表 中，获取到 第fallInGroupIndex个数字
            /* 我们通过 fallInGroupIndex = k / subPermutationAmountPerGroup 算出的，正是“当前位 应选 剩余数字中的第几个”。
            * 模拟了 DFS中 选择 当前分支 的操作 */
            int currentSpotsDigit = availableDigitSeqInAsc.get(fallInGroupIndex);
            // 将 该数字 添加到 目标结果排列 的当前位上
            targetPermutation.append(currentSpotsDigit);
            // 从 可用数字列表(升序) 中 移除 已使用的数字（保持升序）   手段：remove(index)；
            availableDigitSeqInAsc.remove(fallInGroupIndex);

            // 更新 zeroBasedRank 为 在当前组内的偏移量，用于 下一轮计算
            // 关键洞察：组内偏移 = 子问题中的k
            zeroBasedRank = zeroBasedRank % subPermutationAmountPerGroup;
        } /* 在 所有可用字符 用尽之后，第k个排列 也就构造完成 */

        return targetPermutation.toString();
    }
}
