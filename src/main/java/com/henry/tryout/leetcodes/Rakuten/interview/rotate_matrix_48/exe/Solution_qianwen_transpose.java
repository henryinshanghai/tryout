package com.henry.tryout.leetcodes.Rakuten.interview.rotate_matrix_48.exe;

public class Solution_qianwen_transpose {
    public void rotate(int[][] matrix) {
        int size = matrix.length;

        // 先对矩阵进行转置
        for (int currentRow = 0; currentRow < size; currentRow++) {
            for (int currentCol = currentRow + 1; currentCol < size; currentCol++) {
                // 把 主对角线 两侧对称的位置上的元素 进行交换（主对角线上的元素不动）
                int temp = matrix[currentRow][currentCol];
                matrix[currentRow][currentCol] = matrix[currentCol][currentRow];
                matrix[currentCol][currentRow] = temp;
            }
        }


        // 在对矩阵数组中的每一行进行翻转
        for (int currentRow = 0; currentRow < size; currentRow++) {
            for (int currentCol = 0; currentCol < size / 2; currentCol++) {
                // 把 当前列位置 与 其对称位置 上的元素 进行交换（中心位置元素不懂）
                int temp = matrix[currentRow][currentCol];
                // 行保持不变，列 使用 对称位置
                matrix[currentRow][currentCol] = matrix[currentRow][size - 1 - currentCol];
                matrix[currentRow][size - 1 - currentCol] = temp;
            }
        }
    }
}
