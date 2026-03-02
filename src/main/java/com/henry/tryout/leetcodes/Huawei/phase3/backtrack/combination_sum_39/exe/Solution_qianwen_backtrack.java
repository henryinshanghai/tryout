package com.henry.tryout.leetcodes.Huawei.phase3.backtrack.combination_sum_39.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_backtrack {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> validCombiList = new ArrayList<>();
        List<Integer> constructedCandidateCombi = new ArrayList<>();

        backtrack(candidates,
                0,
                target,
                constructedCandidateCombi,
                validCombiList);

        return validCombiList;
    }

    /**
     * 回溯搜索 所有和为 target 的组合
     *
     * @param candidateNums           候选数组（无重复正整数）
     * @param currStartIndex          当前可选的起始索引（防止重复组合）
     * @param currentTarget           剩余需要凑出的目标值
     * @param currentConstructedCombi 当前已选择的数字列表
     * @param validComboList          存储所有有效组合的结果列表
     */
    private void backtrack(int[] candidateNums,
                           int currStartIndex,
                           int currentTarget,
                           List<Integer> currentConstructedCombi,
                           List<List<Integer>> validComboList) {
        /* 构造过程结束 */
        // 🛑 终止条件1：找到一个解
        if (currentTarget == 0) {
            // 收集 找到的组合；    手段：深拷贝！
            validComboList.add(new ArrayList<>(currentConstructedCombi));
            return;
        }

        // 🛑 终止条件2：剩余值为负，不可能再满足
        if (currentTarget < 0) {
            // 返回给 上一级调用
            return;
        }

        /* 从 currStartIndex 开始，构造满足条件的组合 */
        for (int currNumCursor = currStartIndex;
             currNumCursor < candidateNums.length;
             currNumCursor++) {

            // （在选择数字之前）做剪枝
            // 如果 当前数字太大，就不用再尝试选择它了
            if(candidateNums[currNumCursor] > currentTarget) {
                // 直接跳过后继操作
                continue;
            }

            // 选择：为 所构造组合中的当前位置 选择当前数字
            currentConstructedCombi.add(candidateNums[currNumCursor]);

            // 递归地 继续构造组合
            // 🐖 由于元素可以重复选择（题意），因此 下一级递归的可选范围 保持不变👇
            backtrack(candidateNums,
                    currNumCursor, // startIndex参数传入currNumCursor 👆
                    currentTarget - candidateNums[currNumCursor], // 从目标和中 刨除 当前所选的元素的值
                    currentConstructedCombi, // 下一级递归 会基于‘当前构造出的组合’继续构造组合
                    validComboList);

            // 撤销 为当前组合的当前位置 所选择的数字（以便继续尝试新的选择）
            currentConstructedCombi.remove(currentConstructedCombi.size() - 1);
        }
    }
}
