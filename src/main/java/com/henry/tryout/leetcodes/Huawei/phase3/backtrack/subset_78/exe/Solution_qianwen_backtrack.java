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
     * @param currStartIndex  当前考虑的起始索引（避免重复组合）
     * @param constructingSubset 当前已选择的元素列表（部分子集）
     * @param allSubsetList     存储所有子集的结果列表
     */
    private void backtrack(int[] numArr,
                           int currStartIndex,
                           List<Integer> constructingSubset,
                           List<List<Integer>> allSubsetList) {
        /* 收集 当前所构造出的子集 */
        // ✅ 每个递归调用 都代表 一个有效子集（包括 空集）
        // 🐖 进行 深拷贝！
        allSubsetList.add(new ArrayList<>(constructingSubset));

        /* 构造‘当前子集’ */
        // 避免重复子集   手段：使用 currStartIndex 来 收窄 同树层下一次构造子集时 的 可选数字范围
        for (int currNumCursor = currStartIndex; currNumCursor < numArr.length; currNumCursor++) {
            // 为 当前子集 选择当前数字
            constructingSubset.add(numArr[currNumCursor]);

            // 递归地 继续构造‘当前子集’
            backtrack(numArr,
                    currNumCursor + 1, // 同树层下一次构造时，可选范围变小
                    constructingSubset,
                    allSubsetList);

            // 撤销 当前子集的当前数字
            constructingSubset.remove(constructingSubset.size() - 1);
        }
    }
}
