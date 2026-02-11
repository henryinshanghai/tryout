package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.target_sum_494.exe;

import java.util.HashMap;
import java.util.Map;

public class Solution_qianwen_DFS {
    Map<String, Integer> subQuestionIdToItsAnswerMap = new HashMap<>();

    public int findTargetSumWays(int[] numArr, int targetSum) {

        return dfs(numArr,
                0,
                0,
                targetSum
        );
    }

    /*
    📌 总结：重复计算的本质

    问题	    重复原因	                    优化方式
    斐波那契	fib(n) 被多次调用	        缓存 fib(n) 的结果
    目标和	(index, sum) 状态被多次到达	缓存 (index, sum) 的结果

    ✅ 核心思想一致：
    “相同的输入，应该产生 相同的输出，无需 重复计算。”
     */
    private int dfs(int[] numArr,
                    int currentNumCursor,
                    int currentSum,
                    int targetSum) {
        // 如果 当前索引指针 到达了 数组末尾，说明???，则：
        if (currentNumCursor == numArr.length) {
            // 如果 当前总和 == 目标和的话，说明 找到了一种方案，
            // 则：返回1 给 上一级调用，否则 返回0 表示没有找到方案
            return currentSum == targetSum ? 1 : 0;
        }

        // 构造 子问题currentSubQuestion的 唯一状态标识
        // 手段：index,currentSum
        String currentSubQuestion = currentNumCursor + "," + currentSum;

        // 如果 该子问题 在缓存中 已经存在，说明不需要再次计算了，
        if (subQuestionIdToItsAnswerMap.containsKey(currentSubQuestion)) {
            // 则：直接返回 缓存结果(给上一级调用)，避免 重复劳动!
            return subQuestionIdToItsAnswerMap.get(currentSubQuestion);
        }

        /* 使用子问题的解 来 帮助解决原始问题 */
        int totalSchemaAmount =
                dfs(numArr,
                    currentNumCursor + 1,
                    currentSum + numArr[currentNumCursor],
                    targetSum
                ) // 为 当前数字 选择+号 时所能得到的方案数量
                +
                dfs(numArr,
                    currentNumCursor + 1,
                    currentSum - numArr[currentNumCursor],
                    targetSum
                ); // 为 当前数字 选择-号 时所能得到的方案数量

        // 缓存 当前子问题的答案
        subQuestionIdToItsAnswerMap.put(currentSubQuestion, totalSchemaAmount);

        // 返回 最终问题的解
        return totalSchemaAmount;
    }
}
