package com.henry.tryout.leetcodes.Huawei.phase1.array.island_perimeter_463.exe;

// 枚举法：对于一个陆地方格，枚举它的四个方向 来 统计每个方向所贡献的周长
public class Solution_lingshan_enumerate4Dirs {
    public int islandPerimeter(int[][] grid) {
        int rowAmount = grid.length;
        int colAmount = grid[0].length;
        int islandPerimeter = 0;

        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 0; currentCol < colAmount; currentCol++) {
                if (grid[currentRow][currentCol] == 0) {
                    continue;
                }

                // 上：出界或者是水
                if (currentRow == 0 || grid[currentRow - 1][currentCol] == 0) {
                    islandPerimeter++;
                }

                // 下：出界或者是水
                if (currentRow == rowAmount - 1 || grid[currentRow + 1][currentCol] == 0) {
                    islandPerimeter++;
                }

                // 左：出界或者是水
                if (currentCol == 0 || grid[currentRow][currentCol - 1] == 0) {
                    islandPerimeter++;
                }

                // 右：出界或者是水
                if (currentCol == colAmount - 1 || grid[currentRow][currentCol + 1] == 0) {
                    islandPerimeter++;
                }
            }
        }
        return islandPerimeter;
    }
}
