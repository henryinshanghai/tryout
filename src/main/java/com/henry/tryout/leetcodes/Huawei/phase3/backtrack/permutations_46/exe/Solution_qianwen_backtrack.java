package com.henry.tryout.leetcodes.Huawei.phase3.backtrack.permutations_46.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_backtrack {
    public List<List<Integer>> permute(int[] numArr) {

        List<List<Integer>> allPermutationList = new ArrayList<>();
        List<Integer> constructedPermutation = new ArrayList<>();

        // 准备一个标记数组     用于标记 该位置上的元素 是否被使用
        boolean[] spotToIfItsNumBeenUsedInCurrPermu = new boolean[numArr.length];

        backtrack(numArr,
                constructedPermutation,
                spotToIfItsNumBeenUsedInCurrPermu,
                allPermutationList);

        return allPermutationList;
    }

    /**
     * 回溯 生成 全排列
     * @param numArr   原始数组（无重复元素）
     * @param constructingPermutation   当前已选择的元素列表（部分排列）
     * @param spotToIfItsNumBeenUsedInCurrPermu   used[i] 表示 nums[i] 是否已被选入 path
     * @param allPermutationList 存储所有完整排列的结果列表
     */
    private void backtrack(int[] numArr,
                           List<Integer> constructingPermutation,
                           boolean[] spotToIfItsNumBeenUsedInCurrPermu,
                           List<List<Integer>> allPermutationList) {

        // 🛑 终止条件：
        // 如果 path 长度等于 nums 长度，说明 找到一个完整排列，
        if (constructingPermutation.size() == numArr.length) {
            // 则：把构造出的排列 添加到 结果集中
            // 🐖 必须深拷贝！
            allPermutationList.add(new ArrayList<>(constructingPermutation));
            // 返回 上一级调用
            return;
        }

        // 🔁 遍历 所有元素（排列问题 必须从 0 开始遍历）
        for (int currentNumSpot = 0; currentNumSpot < numArr.length; currentNumSpot++) {
            // ❌ 跳过 当前排列中 已使用的元素
            if (spotToIfItsNumBeenUsedInCurrPermu[currentNumSpot]) {
                continue;
            }

            // ➕ 做选择：将 nums[currentNumSpot] 加入 path，并 标记为 已使用
            constructingPermutation.add(numArr[currentNumSpot]);
            spotToIfItsNumBeenUsedInCurrPermu[currentNumSpot] = true;

            // 🔁 递归：继续选择 下一个位置的元素
            backtrack(numArr,
                    constructingPermutation,
                    spotToIfItsNumBeenUsedInCurrPermu,
                    allPermutationList);

            // ↩ 撤销选择（回溯）：恢复状态，尝试 其他可能性
            constructingPermutation.remove(constructingPermutation.size() - 1);
            spotToIfItsNumBeenUsedInCurrPermu[currentNumSpot] = false;
        }
    }
}
