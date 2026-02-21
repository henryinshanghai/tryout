package com.henry.tryout.leetcodes.Rakuten.interview.rotate_matrix_48.exe;

// 旋转法 需要计算出 参与旋转的四个位置坐标，有点子复杂
public class Solution_qianwen_rotation_in_group {
    public void rotate(int[][] matrix) {
        int size = matrix.length;

        // 以左上角1/4的元素作为起点 进行四个位置轮转
        for (int currentRow = 0; currentRow < size / 2; currentRow++) {
            for (int currentCol = 0; currentCol < (size + 1) / 2; currentCol++) {

                int temp = matrix[currentRow][currentCol];

                /* 把四元环中的各个元素 按照顺序 从其初始位置 搬移到 目标位置 */
                // (SX, SY) => (TX, TY)
                // SY = TX; SX + TY = size - 1;
                // SX = size - 1 - TY; SY = TX

                // 具体目标位置(currentRow, currentCol) - 左上角
                // 计算出的起始位置(size - 1 - currentCol, currentRow) - 左下角
                matrix[currentRow][currentCol] = matrix[size - 1 - currentCol][currentRow];

                // 右下角 -> 左下角
                // 左下角位置 (size - 1 - currentCol, currentRow)
                // 右下角位置 SX = size - 1 - currentRow; SY = size - 1 - currentCol;
                matrix[size - 1 - currentCol][currentRow] = matrix[size - 1 - currentRow][size - 1 - currentCol];

                // 右上角 -> 右下角
                // 右下角位置 (size - 1 - currentRow, size - 1 - currentCol)
                // 右上角位置 SX = size - 1 - (size - 1 - currentCol) = currentCol; SY = size - 1 - currentRow;
                matrix[size - 1 - currentRow][size - 1 - currentCol] = matrix[currentCol][size - 1 - currentRow];

                // temp -> 右上角
                matrix[currentCol][size - 1 - currentRow] = temp;

                /* 四元组四个位置上的元素完成了一次轮换，所有四个元素都被排定 */
            }
        }
    }
}
