package com.henry.tryout.leetcodes.Huawei.phase2.arr.form_target_array_1764.exe;

public class Solution_qianwen_searchSubstring {
    public boolean canChoose(int[][] subNumSeqs, int[] mainNumSeq) {
        int cursorOfMainNum = 0; // 主序列中的数字指针
        int mainSeqNumAmount = mainNumSeq.length; // 主序列中的数字数量

        // 对于 每一个 子序列，按序 从 主序列 中 查找它
        for (int[] currentSubNumSeq : subNumSeqs) {
            int currentSubSeqNumAmount = currentSubNumSeq.length;
            boolean foundCurrentSubSeq = false;

            /* 在 主序列中，从 数字指针处 开始查找 当前数字子序列 */
            while (cursorOfMainNum <= mainSeqNumAmount - currentSubSeqNumAmount) {
                // 如果 当前数字指针所指向的位置 是 当前数字子序列的 一个匹配起始点，说明 当前数字子序列 被完全匹配，则：
                if (isAMatchFrom(cursorOfMainNum, currentSubNumSeq, mainNumSeq)) {
                    // ① 把 主序列中的数字指针 向后移动到 未匹配过的字符
                    cursorOfMainNum += currentSubSeqNumAmount;
                    // ② 标记 找到了 当前数字子序列
                    foundCurrentSubSeq = true;
                    // ③ 跳出while循环，以 继续处理 下一个 数字子序列
                    break;
                }

                // 如果 从当前数字指针 指向的位置 开始 尝试匹配 当前数字子序列 失败，说明 当前位置 不是一个正确的匹配起始点，
                // 则：移动数字指针到 下一个位置，继续尝试匹配 当前数字子序列
                cursorOfMainNum++;
            }

            // 循环结束后，检查 标识变量 来 判断 是不是找见了 当前数字序列的匹配。
            // 如果 没有找到 匹配，说明 当前数字子序列 在 主序列中不存在，
            if (!foundCurrentSubSeq) {
                // 则：整体任务失败，返回false
                return false;
            }

            /* 否则，说明 找到了 当前数字子序列的匹配，则：继续尝试 找下一个数字子序列的匹配 */
        }

        return true;
    }

    // 判断 mainNumSeq 从 startSpot 开始 是否等于 subNumSeq
    private boolean isAMatchFrom(int spotInMainSeq, int[] subNumSeq, int[] mainNumSeq) {
        for (int currentNumSpot = 0; currentNumSpot < subNumSeq.length; currentNumSpot++) {
            // 如果 当前位置 上 主序列中的数字 与 子序列中的数字 不相同，说明 字符失配，则：
            if (mainNumSeq[spotInMainSeq + currentNumSpot] != subNumSeq[currentNumSpot]) {
                // 不匹配
                return false;
            }
        }

        // 整个子序列匹配
        return true;
    }
}
