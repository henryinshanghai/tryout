package com.henry.tryout.leetcodes.Huawei.phase1.str.is_subsequence_392.exe;

//  这种写法 实际上 更高效一点，因为它 一旦确认 s 已完全匹配，就立刻返回，不再 浪费时间扫描 t的剩余部分
public class Solution_Krahets_2_cursors {
    public boolean isSubsequence(String triedCharSeq, String textCharSeq) {
        if (triedCharSeq.length() == 0) {
            return true;
        }

        for (int cursorOfTriedSeq = 0, cursorOfCharSeq = 0; cursorOfCharSeq < textCharSeq.length(); cursorOfCharSeq++) {

            // 如果 尝试序列中的当前字符 与 文本序列中的当前字符 相等，说明 该字符 被匹配，则：
            if (triedCharSeq.charAt(cursorOfTriedSeq) == textCharSeq.charAt(cursorOfCharSeq)) {
                // ① 把 尝试序列中的字符指针 向右移动一个位置
                // ② 移动后，检查 尝试序列中的字符 是不是 已经完全匹配了
                // 如果是，说明 尝试序列 是一个 有效的子序列，
                if (++cursorOfTriedSeq == triedCharSeq.length()) {
                    // 则：返回true
                    return true;
                }
            }
        }

        // 如果 循环结束后 仍旧没有return，说明 没能匹配完 尝试序列中的字符，
        // 因此 它不是一个有效的子序列，返回false
        return false;
    }
}
