package com.henry.tryout.leetcodes.Huawei.phase3.backtrack.combination_70.exe;

import java.util.ArrayList;
import java.util.List;

public class Solution_qianwen_backtrack {
    List<List<Integer>> allValidCombiList = new ArrayList<>();
    List<Integer> currValidCombi = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k);
        return allValidCombiList;
    }

    /**
     * @param currStartIndex 当前层从哪个数字开始选 (保证不重复、有序)
     * @param n 上限
     * @param k 目标长度
     */
    private void backtrack(int currStartIndex, int n, int k) {
        // 1. 终止条件：路径长度达到 k
        if (currValidCombi.size() == k) {
            // 🐖：这里一定要 new一个新的副本，否则 存的是引用
            allValidCombiList.add(new ArrayList<>(currValidCombi));
            return;
        }

        // 2. 单层搜索逻辑
        // 剪枝优化：i <= n - (k - path.size()) + 1
        // 解释：如果 还需要选 (k - path.size()) 个数，
        // 那么 i 最大只能取到 (n - 剩余数量 + 1)
        // 再往后取，剩下的数字 就不够凑齐 k个了
        for (int currentNumCursor = currStartIndex; currentNumCursor <= n - (k - currValidCombi.size()) + 1; currentNumCursor++) {
            // 做选择
            currValidCombi.add(currentNumCursor);

            // 递归：下一层 从currentNumCursor+1开始选，保证 不回头选
            backtrack(currentNumCursor + 1, n, k);

            // 撤销选择 (回溯)
            currValidCombi.remove(currValidCombi.size() - 1);
        }
    }
}
