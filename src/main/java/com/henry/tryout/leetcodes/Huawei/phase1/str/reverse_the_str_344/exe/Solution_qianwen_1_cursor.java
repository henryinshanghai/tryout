package com.henry.tryout.leetcodes.Huawei.phase1.str.reverse_the_str_344.exe;

public class Solution_qianwen_1_cursor {
    public void reverseString(char[] s) {
        int charAmount = s.length;

        // 只需 遍历前一半的元素，把它们 与 其对称位置上的元素 进行交换
        for (int currentSpot = 0; currentSpot < charAmount / 2; currentSpot++) {
            // 交换 s[currentSpot] 和 它的对称位置 s[n - 1 - currentSpot]
            char temp = s[currentSpot];
            int itsSymmetricalSpot = charAmount - 1 - currentSpot;
            s[currentSpot] = s[itsSymmetricalSpot];
            s[itsSymmetricalSpot] = temp;
        }
    }
}
