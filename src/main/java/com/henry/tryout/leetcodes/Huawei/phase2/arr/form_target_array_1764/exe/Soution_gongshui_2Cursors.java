package com.henry.tryout.leetcodes.Huawei.phase2.arr.form_target_array_1764.exe;

// 这种写法 必须要遍历完 整个主序列，不会提前返回false
public class Soution_gongshui_2Cursors {
    public boolean canChoose(int[][] subNumSeqs, int[] mainNumSeq) {
        int numAmountInMainSeq = mainNumSeq.length;
        int subSeqAmount = subNumSeqs.length;

        int matchedSubSeqAmount = 0;

        // 遍历主序列中的数字
        for (int cursorOfMainSeq = 0, currentSubSeq = 0; // 当前尝试匹配的子序列序号
             cursorOfMainSeq < numAmountInMainSeq &&
             currentSubSeq < subSeqAmount; ) {

            /* 尝试 从 主序列的数字指针 所指向的当前位置 开始 进行一次子序列匹配 */
            // 如果 匹配成功，
            if (findAMatchOn(cursorOfMainSeq, mainNumSeq, subNumSeqs[currentSubSeq])) {
                // 快进 主序列的数字指针
                cursorOfMainSeq += subNumSeqs[currentSubSeq++].length;
                // 成功匹配的子串数量 + 1
                matchedSubSeqAmount++;
            } else { // 如果 没有匹配成功，说明 当前位置 无法产生匹配，
                // 则：移动 主序列的数字指针 到下一个位置
                cursorOfMainSeq++;
            }
        }

        // 检查 成功匹配的子串数量 是不是 与所有子串的数量 相等
        return matchedSubSeqAmount == subSeqAmount;
    }

    boolean findAMatchOn(int cursorOfMainSeq, int[] mainNumSeq, int[] subNumSeq) {
        int cursorOfSubSeq = 0;

        for (;
             cursorOfSubSeq < subNumSeq.length && cursorOfMainSeq < mainNumSeq.length;
             cursorOfSubSeq++, cursorOfMainSeq++) {
            if (subNumSeq[cursorOfSubSeq] != mainNumSeq[cursorOfMainSeq]) {
                return false;
            }
        }

        // 如果子串被完全匹配，指针一定会移动到末尾
        return cursorOfSubSeq == subNumSeq.length;
    }
}
