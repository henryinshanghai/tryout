package com.henry.tryout.leetcodes.Huawei.phase1.str.is_subsequence_392.exe;

//  这种写法 实际上 更高效一点，
//  因为它 一旦确认 s 已完全匹配，就立刻返回，不再 浪费时间扫描 t的剩余部分。
public class Solution_Krahets_2_cursors {
    public boolean isSubsequence(String triedCharSeq, String textCharSeq) {
        if (triedCharSeq.length() == 0) {
            return true;
        }

        for (int cursorOfTriedSeq = 0, cursorOfCharSeq = 0; cursorOfCharSeq < textCharSeq.length(); cursorOfCharSeq++) {

            // 如果 尝试序列中的当前字符 与 文本序列中的当前字符 相等，
            // 说明 该字符 被匹配，
            if (triedCharSeq.charAt(cursorOfTriedSeq) == textCharSeq.charAt(cursorOfCharSeq)) {
                /* 则：向后移动尝试序列的字符指针，并 检查是否到达末尾 */
                // 如果是，说明 尝试序列 是一个 有效的子序列，
                if (++cursorOfTriedSeq == triedCharSeq.length()) {
                    // 则：直接返回true
                    return true;
                }
            }
        }

        // 如果 循环结束后 仍旧没有return，
        // 说明 没能匹配完 尝试序列中的字符，
        // 因此 它不是一个有效的子序列，返回false
        return false;
    }
}
