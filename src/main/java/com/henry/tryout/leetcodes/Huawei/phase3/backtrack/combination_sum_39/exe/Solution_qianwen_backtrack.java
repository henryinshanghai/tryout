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
     * @param candidateNums 候选数组（无重复正整数）
     * @param startIndexAnchor      当前可选的起始索引（防止重复组合）
     * @param currentTarget     剩余需要凑出的目标值
     * @param currentConstructedCombi       当前已选择的数字列表
     * @param validComboList     存储所有有效组合的结果列表
     */
    private void backtrack(int[] candidateNums,
                           int startIndexAnchor,
                           int currentTarget,
                           List<Integer> currentConstructedCombi,
                           List<List<Integer>> validComboList) {

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

        // 🔁 从 start 开始遍历，避免重复组合
        for (int pickedNumCursor = startIndexAnchor;
             pickedNumCursor < candidateNums.length;
             pickedNumCursor++) {

            // ➕ 做选择：选择 candidates[pickedNumCursor]
            currentConstructedCombi.add(candidateNums[pickedNumCursor]);

            // 🔁 递归：继续从 pickedNumCursor 开始选（允许重复选当前元素）
            backtrack(candidateNums,
                    pickedNumCursor,
                    currentTarget - candidateNums[pickedNumCursor],
                    currentConstructedCombi,
                    validComboList);

            // ↩️ 撤销选择（回溯）
            currentConstructedCombi.remove(currentConstructedCombi.size() - 1);
        }
    }
}
