package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.number_of_islands_200.exe;

public class Solution_qianwen_DFS {

    /**
     * 计算 二维网格中 岛屿的数量。
     * 使用 深度优先搜索（DFS）将每个岛屿“淹没”（标记为 '0'），
     * 每次发现 新陆地 就计数 +1。
     *
     * @param gridToItsValue 由 '1'（陆地）和 '0'（水）组成的二维字符数组
     * @return 岛屿的数量
     */
    public int numIslands(char[][] gridToItsValue) {
        if (gridToItsValue == null || gridToItsValue.length == 0) {
            return 0;
        }

        int rowAmount = gridToItsValue.length;
        int colAmount = gridToItsValue[0].length;
        int islandAmount = 0;

        // 遍历每一个格子
        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 0; currentCol < colAmount; currentCol++) {
                // 如果发现一块 未访问的陆地（'1'）
                if (gridToItsValue[currentRow][currentCol] == '1') {
                    islandAmount++;          // 找到一个新岛屿
                    dfs(gridToItsValue, currentRow, currentCol);        // 用 DFS 把 整个岛屿 淹没（标记为 '0'）
                }
            }
        }

        return islandAmount;
    }

    /**
     * 深度优先搜索：从 (row, col) 开始，将所有相连的 '1' 变成 '0'。
     *
     * @param gridToItsValue 二维网格
     * @param currentRow     当前行索引
     * @param currentCol     当前列索引
     */
    private void dfs(char[][] gridToItsValue, int currentRow, int currentCol) {
        int rowAmount = gridToItsValue.length;
        int colAmount = gridToItsValue[0].length;

        // 边界检查：越界 或 遇到水（'0'），直接返回
        if (currentRow < 0 || currentRow >= rowAmount
                || currentCol < 0 || currentCol >= colAmount
                || gridToItsValue[currentRow][currentCol] == '0') {
            return;
        }

        // 将 当前陆地 标记为 已访问（淹没为水）
        gridToItsValue[currentRow][currentCol] = '0';

        // 递归访问 当前方格的四个方向：上、下、左、右
        dfs(gridToItsValue, currentRow - 1, currentCol); // 上
        dfs(gridToItsValue, currentRow + 1, currentCol); // 下
        dfs(gridToItsValue, currentRow, currentCol - 1); // 左
        dfs(gridToItsValue, currentRow, currentCol + 1); // 右
    }
}
