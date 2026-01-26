package com.henry.tryout.leetcodes.Huawei.phase1.str.is_subsequence_392.exe;

public class Solution_qianwen_2_cursors {
    public boolean isSubsequence(String triedCharSeq, String textCharSeq) {
        int cursorOfTriedSeq = 0, cursorOfSourceSeq = 0;

        while (cursorOfTriedSeq < triedCharSeq.length() && cursorOfSourceSeq < textCharSeq.length()) {
            // 如果 尝试序列中的当前字符 与 文本序列中的当前字符 相同，说明 在 文本序列中 找到了一个 字符匹配，则：
            if (triedCharSeq.charAt(cursorOfTriedSeq) == textCharSeq.charAt(cursorOfSourceSeq)) {
                // 把 尝试序列中的指针 向后移动一个位置
                cursorOfTriedSeq++;
            }

            // 把文本序列中的指针 向后移动一个位置   来 获取下一个文本字符
            cursorOfSourceSeq++;
        }

        // 如果 尝试序列的字符指针 移动到了 其末尾位置，说明 尝试序列中所有的字符 都在文本序列中 顺序匹配，则：
        // 该尝试序列 是 文本序列的 一个子序列
        return cursorOfTriedSeq == triedCharSeq.length();
    }
}
