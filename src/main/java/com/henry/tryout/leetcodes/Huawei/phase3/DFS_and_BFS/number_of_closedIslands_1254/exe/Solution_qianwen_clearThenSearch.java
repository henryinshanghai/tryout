package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.number_of_closedIslands_1254.exe;

public class Solution_qianwen_clearThenSearch {
    public int closedIsland(int[][] grid) {
        int rowAmount = grid.length, colAmount = grid[0].length;

        // Step 1: 清除所有 与边界相连的岛屿
        // 手段：以边界上的陆地方格为起点 进行DFS 来 把边界岛屿中的方格都改变为 水方格
        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 0; currentCol < colAmount; currentCol++) {
                if ((currentRow == 0 || currentRow == rowAmount - 1 ||
                        currentCol == 0 || currentCol == colAmount - 1) &&
                        grid[currentRow][currentCol] == 0) {
                    // 从 边界陆地方格 出发，淹没 整个岛屿
                    dfs(grid, currentRow, currentCol);
                }
            }
        }

        // Step 2: 统计 剩余岛屿数量（此时 所有岛屿 都是 封闭的）
        // 🐖 这个就是标准的二维矩阵中的DFS了
        int closedIslandAmount = 0;
        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 0; currentCol < colAmount; currentCol++) {
                if (grid[currentRow][currentCol] == 0) {
                    closedIslandAmount++;
                    // 以 指定位置为起点，搜索所有与之相连通的方格（陆地方格）
                    dfs(grid, currentRow, currentCol);
                }
            }
        }
        return closedIslandAmount;
    }

    // 传统DFS写法：先检查当前方格，再标记当前方格，最后递归搜索所有的邻居方格
    private void dfs(int[][] grid, int currentRow, int currentCol) {
        // 边界检查 + 陆地检查
        if (currentRow < 0 || currentRow >= grid.length ||
                currentCol < 0 || currentCol >= grid[0].length || // 对 当前方格的边界检查
                grid[currentRow][currentCol] != 0) { // 业务检查：当前方格 是不是 陆地方格
            // 如果 检查不通过，说明 当前方向的扩展结束，则：
            // 直接 return到 上一级调用
            return;
        }

        // 淹没当前的陆地方格（标记为已访问/水域）
        grid[currentRow][currentCol] = 1;

        // 向 当前方格的所有四个方向 递归地搜索
        dfs(grid, currentRow + 1, currentCol);
        dfs(grid, currentRow - 1, currentCol);
        dfs(grid, currentRow, currentCol + 1);
        dfs(grid, currentRow, currentCol - 1);
    }
}
