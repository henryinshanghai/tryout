package com.henry.tryout.leetcodes.Huawei.phase3.dynamic_programming.min_path_sum_64.exe;

public class Solution_qianwen_dp {
    public int minPathSum(int[][] gridToItsCost) {
        // 处理特殊的入参
        if (gridToItsCost == null ||
                gridToItsCost.length == 0 ||
                gridToItsCost[0].length == 0)
            return 0;

        int rowAmount = gridToItsCost.length;
        int colAmount = gridToItsCost[0].length;
        // ① dp[][]数组及下标的含义：
        // 当前方格 -> 到达当前方格的路径的加和值
        int[][] gridToMinPathSumReachToIt = new int[rowAmount][colAmount];

        /* ③ dp[][]元素的初始化 */
        // 初始化dp[0][0]
        gridToMinPathSumReachToIt[0][0] = gridToItsCost[0][0];

        // 初始化第一行的dp[][]元素
        for (int currentColumn = 1; currentColumn < colAmount; currentColumn++) {
            gridToMinPathSumReachToIt[0][currentColumn] =
                    gridToMinPathSumReachToIt[0][currentColumn - 1]
                            + gridToItsCost[0][currentColumn];
        }

        // 初始化第一列的dp[][]元素
        for (int currentRow = 1; currentRow < rowAmount; currentRow++) {
            gridToMinPathSumReachToIt[currentRow][0] =
                    gridToMinPathSumReachToIt[currentRow - 1][0]
                            + gridToItsCost[currentRow][0];
        }

        /* ④ 递推出 剩余的dp[][]元素 */
        for (int currentRow = 1; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 1; currentCol < colAmount; currentCol++) {
                // ② 递推公式：dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])
                gridToMinPathSumReachToIt[currentRow][currentCol] =
                        gridToItsCost[currentRow][currentCol]
                                + Math.min(gridToMinPathSumReachToIt[currentRow - 1][currentCol],
                                gridToMinPathSumReachToIt[currentRow][currentCol - 1]);
            }
        }

        // ⑤ 根据题意，按需返回dp[][]元素
        return gridToMinPathSumReachToIt[rowAmount - 1][colAmount - 1];
    }
}
