package com.henry.tryout.leetcodes.Huawei.phase1.array.island_perimeter_463.exe;

// 计算法：对于一个新的陆地方格，先把 它为周长的贡献 计为4，
// 再根据 其左侧邻居 与 上方邻居的情况（旧的陆地方格）来 减掉 多余累计的共享边；
// 原理：在 遍历到当前方格时，其上方方格、左侧方格 必然都已经遍历过了
public class Solution_qianwen_calculate4CurrSquare {
    public int islandPerimeter(int[][] grids) {
        int islandPerimeter = 0;
        int rowAmount = grids.length;
        int colAmount = grids[0].length;

        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 0; currentCol < colAmount; currentCol++) {
                // 如果 当前方格是 陆地方格，说明 它可能会为岛屿 贡献周长，则：
                if (grids[currentRow][currentCol] == 1) {
                    // 先 把当前方格的贡献 计为4
                    islandPerimeter += 4;

                    /* 再 检查 当前方格的旧邻居方格（上方方格、左侧方格） */
                    // 如果 上方方格是陆地方格，
                    // 说明 它与当前陆地方格之间 存在共享边 被多余累计，
                    if (currentRow > 0 && grids[currentRow - 1][currentCol] == 1) {
                        // 则：从周长中 移除 共享边的贡献（贡献为2 - 上方方格 贡献1，当前方格 贡献1）
                        islandPerimeter -= 2;
                    }

                    // 如果 左侧方格是陆地方格，
                    // 说明 它与当前陆地方格之间 存在共享边 被多余累计，
                    if (currentCol > 0 && grids[currentRow][currentCol - 1] == 1) {
                        // 则：从周长中 移除 共享边的贡献（贡献为2 - 左侧方格 贡献为1，当前方格 贡献1）
                        islandPerimeter -= 2;
                    }
                }
            }
        }

        return islandPerimeter;
    }
}
