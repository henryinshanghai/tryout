package com.henry.tryout.leetcodes.Huawei.phase1.array.island_perimeter_463.exe;

public class Solution_qianwen_add4Minus2 {
    public int islandPerimeter(int[][] grids) {
        int islandPerimeter = 0;
        int rowAmount = grids.length;
        int colAmount = grids[0].length;

        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 0; currentCol < colAmount; currentCol++) {
                // 如果 当前方格是 陆地方格，说明 它可能会为岛屿 贡献周长，则：
                if (grids[currentRow][currentCol] == 1) {
                    // 先把周长+4
                    islandPerimeter += 4;

                    /* 检查 上方 是否是 陆地方格 */
                    // 如果是，说明 该方格的四条边 都已经 被计入周长（而 共享边 不应该被计入），则：
                    if (currentRow > 0 && grids[currentRow - 1][currentCol] == 1) {
                        // 从周长中 移除 共享边的贡献（贡献为2 - 上方方格 贡献1，当前方格 贡献1）
                        islandPerimeter -= 2;
                    }

                    /* 检查 左方 是否是 陆地方格 */
                    // 如果是，说明 该方格的四条边 都已经 被计入周长（而 共享边 不应该被计入），则：
                    if (currentCol > 0 && grids[currentRow][currentCol - 1] == 1) {
                        // 从周长中 移除 共享边的贡献（贡献为2 - 左侧方格 贡献为1，当前方格 贡献1）
                        islandPerimeter -= 2;
                    }
                }
            }
        }

        return islandPerimeter;
    }
}
