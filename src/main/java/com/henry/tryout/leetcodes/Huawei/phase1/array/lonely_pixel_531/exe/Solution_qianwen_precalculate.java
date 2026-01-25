package com.henry.tryout.leetcodes.Huawei.phase1.array.lonely_pixel_531.exe;

public class Solution_qianwen_precalculate {
    public int findLonelyPixel(char[][] pixelMatrix) {
        /* 边界条件 */
        if (pixelMatrix == null ||
                pixelMatrix.length == 0 ||
                pixelMatrix[0].length == 0) {
            return 0;
        }

        int rowAmount = pixelMatrix.length;
        int colAmount = pixelMatrix[0].length;

        // 步骤1: 预处理 —— 统计每行、每列的 'B' 数量
        int[] rowToItsBAmount = new int[rowAmount];
        int[] colToItsBAmount = new int[colAmount];

        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 0; currentCol < colAmount; currentCol++) {
                if (pixelMatrix[currentRow][currentCol] == 'B') {
                    rowToItsBAmount[currentRow]++;
                    colToItsBAmount[currentCol]++;
                }
            }
        }

        // 步骤2: 遍历 每个 'B'，检查 是否孤独
        int lonelyBAmount = 0;
        for (int currentRow = 0; currentRow < rowAmount; currentRow++) {
            for (int currentCol = 0; currentCol < colAmount; currentCol++) {
                if (pixelMatrix[currentRow][currentCol] == 'B'
                        && rowToItsBAmount[currentRow] == 1 // 当前行中 只有一个B
                        && colToItsBAmount[currentCol] == 1) { // 当前列中 也只有一个B
                    lonelyBAmount++;
                }
            }
        }

        return lonelyBAmount;
    }
}
