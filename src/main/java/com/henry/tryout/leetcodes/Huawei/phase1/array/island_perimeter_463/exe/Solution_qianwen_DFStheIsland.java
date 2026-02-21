package com.henry.tryout.leetcodes.Huawei.phase1.array.island_perimeter_463.exe;

// DFS法：从陆地方格开始，向 所有的4个方向 进行搜索，并 统计 各个方向上 对周长的贡献；
public class Solution_qianwen_DFStheIsland {
    public int islandPerimeter(int[][] grids) {
        int rowAmount = grids.length;
        int colAmount = grids[0].length;

        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 0; currentCol < colAmount; currentCol++) {
                // 如果 当前方格的值为1，说明 是陆地方格，则：
                if (grids[currentRow][currentCol] == 1) {
                    // 以它作为起点，在矩阵中 开始DFS -
                    // ① 标记所有 与之相连通的陆地方格（也就是 岛屿中的所有方格）
                    // ② 累计 其各个方向上的 周长贡献
                    return dfs(grids, currentRow, currentCol);
                }
            }
        }
        return 0;
    }

    /**
     * 🌟 核心思想：
     * 周长 = 所有陆地格子“面向外部的边”的总和
     * DFS 通过“遇到外部就返回 1”来精确统计这些边。
     *
     * @param grids      方格的二维数组
     * @param currentRow 当前方格的行坐标
     * @param currentCol 当前方格的列坐标
     * @return 以当前方格作为起点的 岛屿区域的周长贡献（暴露在外的边的数量）
     */
    private int dfs(int[][] grids, int currentRow, int currentCol) {
        /* 每当在 DFS 遍历中，从 一个岛屿方格 走向 一个非陆地方格，就 将周长加 1 */
        // 如果 当前方向的方格 是非陆地方格（越界 或 遇到水），
        // 说明 它会为周长 贡献一条边，
        if (currentRow < 0 || currentRow >= grids.length // 行坐标 越界
                || currentCol < 0 || currentCol >= grids[0].length // 列坐标 越界
                || grids[currentRow][currentCol] == 0) { // 当前方格 是 水方格
            // 则：为 上一级调用 返回1(周长+1)；
            return 1;
        }

        // 如果 当前方格的值 是-1，
        // 说明 它是一个 已经搜索过的方格（岛屿内部陆地），
        // 不会再 为周长 贡献边 了，则：
        if (grids[currentRow][currentCol] == -1) {
            // 为 上一级调用 返回0；
            return 0;
        }

        // 搜索到 当前方格，把 当前方格 标记为 已访问（避免重复处理）
        grids[currentRow][currentCol] = -1;

        // 递归地搜索 当前方格的四个方向（上下左右），并 把每个方向上 得到的边数 累加起来
        return dfs(grids, currentRow - 1, currentCol) +
                dfs(grids, currentRow + 1, currentCol) +
                dfs(grids, currentRow, currentCol - 1) +
                dfs(grids, currentRow, currentCol + 1);
    }
}
