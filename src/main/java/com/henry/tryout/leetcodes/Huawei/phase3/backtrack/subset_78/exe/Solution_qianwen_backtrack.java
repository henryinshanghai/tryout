package com.henry.tryout.leetcodes.Huawei.phase3.backtrack.subset_78.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_backtrack {
    public List<List<Integer>> subsets(int[] numArr) {
        List<List<Integer>> allSubsetList = new ArrayList<>();

        backtrack(numArr,
                0,
                new ArrayList<>(),
                allSubsetList);

        return allSubsetList;
    }

    /**
     * 回溯生成所有子集
     * @param numArr            原始数组（无重复元素）
     * @param startIndexAnchor  当前考虑的起始索引（避免重复组合）
     * @param constructingSubset 当前已选择的元素列表（部分子集）
     * @param allSubsetList     存储所有子集的结果列表
     */
    private void backtrack(int[] numArr,
                           int startIndexAnchor,
                           List<Integer> constructingSubset,
                           List<List<Integer>> allSubsetList) {

        // ✅ 每个递归调用 都代表 一个有效子集（包括 空集）
        // 进行 深拷贝！
        allSubsetList.add(new ArrayList<>(constructingSubset));

        // 🔁 从 start 开始遍历 来 保证从可选范围中选择（避免重复子集 [1,2] 和 [2,1]）
        for (int pickedNumCursor = startIndexAnchor; pickedNumCursor < numArr.length; pickedNumCursor++) {
            // ➕ 做选择 选择当前元素 添加到子集中
            constructingSubset.add(numArr[pickedNumCursor]);

            // 🔁 递归：下一层从 pickedNumCursor+1 开始
            backtrack(numArr,
                    pickedNumCursor + 1, // 收缩可选范围
                    constructingSubset,
                    allSubsetList);

            // ↩️ 撤销 当前所选择的元素（回溯）
            constructingSubset.remove(constructingSubset.size() - 1);
        }
    }
}
