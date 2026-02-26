package com.henry.tryout.leetcodes.Huawei.phase3.two_cursors.threeSum_closest_16.exe;

import java.util.Arrays;

public class Solution_qianwen_2Cursors {
    public int threeSumClosest(int[] numArr, int targetSum) {
        // 对数组元素进行排序，得到 有序的数字序列
        Arrays.sort(numArr);

        int numAmount = numArr.length;
        // ① 准备一个 ‘最接近target的sum’变量   初始化大小为 有序数组的前3项之和
        int closestSum = numArr[0] + numArr[1] + numArr[2];

        // 从0开始，遍历每一个位置
        for (int currentNumSpot = 0; currentNumSpot < numAmount - 2; currentNumSpot++) {
            // 初始化 当前位置的两个bro的位置
            int middleBroCursor = currentNumSpot + 1;
            int bigBroCursor = numAmount - 1;

            // 不断按需调整两个bro的位置
            while (middleBroCursor < bigBroCursor) {
                /* ② 计算 当前三兄弟的加和结果 */
                int sumOfCurrentCombo =
                        numArr[currentNumSpot] + numArr[middleBroCursor] + numArr[bigBroCursor];

                /* ③ 尝试使用 当前加和结果 来 更新‘最接近target的加和结果’ */
                // 如果 当前三元组的加和 与 目标加和 差的绝对值 小于 ‘当前最接近目标和的三元组的加和’ 与 目标加和 的差的绝对值，
                // 说明 当前三元组的加和 距离 目标加和更近，
                if (Math.abs(sumOfCurrentCombo - targetSum) < Math.abs(closestSum - targetSum)) {
                    // 则：使用它 更新 ‘最接近目标和的加和’
                    closestSum = sumOfCurrentCombo;
                }

                /* ④ 按需更新兄弟指针的位置 来 继续尝试找到‘最接近target的加和结果’ */
                // 如果 当前加和 刚好等于 目标和，说明找到了一个解，
                if (sumOfCurrentCombo == targetSum) {
                    // 则：直接返回 该加和（等于目标加和）
                    return targetSum;
                } else if (sumOfCurrentCombo < targetSum) { // 如果 当前加和 小于 目标和，则：
                    // 向右移动 左指针 来 得到 更大的加和
                    middleBroCursor++;
                } else { // 否则，
                    // 向左移动右指针 来 得到 更小的和
                    bigBroCursor--;
                }
            }
        }

        // 返回 ‘最接近目标和’的加和结果
        return closestSum;
    }
}
