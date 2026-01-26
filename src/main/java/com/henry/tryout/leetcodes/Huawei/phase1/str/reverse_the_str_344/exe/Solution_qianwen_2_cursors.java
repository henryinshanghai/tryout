package com.henry.tryout.leetcodes.Huawei.phase1.str.reverse_the_str_344.exe;

public class Solution_qianwen_2_cursors {
    public void reverseString(char[] s) {
        // 初始化 左指针、右指针的位置
        int leftCharCursor = 0;
        int rightCharCursor = s.length - 1;

        // 当 左指针 小于 右指针 时，继续交换
        while (leftCharCursor < rightCharCursor) {
            // 交换 s[leftCharCursor] 和 s[rightCharCursor]
            char temp = s[leftCharCursor];
            s[leftCharCursor] = s[rightCharCursor];
            s[rightCharCursor] = temp;

            // 向内移动
            leftCharCursor++;
            rightCharCursor--;
        }
    }
}
