package com.henry.tryout.leetcodes.Huawei.phase1.str.is_valid_palindrome_125.exe;

// 先移动指针 指向有效字符，再 对指针指向的字符 进行比较（推荐写法）
public class Solution_qianwen_2_cursors {
    public boolean isPalindrome(String s) {
        int leftCharCursor = 0;
        int rightCharCursor = s.length() - 1;

        /* 步骤：逐个比较 当前字母字符对中的两个字符 是否相等 */
        while (leftCharCursor < rightCharCursor) {
            /* 使用while循环 来 移动指针到 合法位置（类似 快速排序的操作） */
            // 当 左字符指针 所指向的字符 不是 合法字符时，
            while (leftCharCursor < rightCharCursor && // 条件① 左字符指针 小于 右字符指针
                    !Character.isLetterOrDigit(s.charAt(leftCharCursor))) { // 条件② 左字符指针所指向的字符 不是 字母字符 或 数字字符
                // 把 左字符指针 向右移动（跳过非法字符）
                leftCharCursor++;
            }
            // 当 右字符指针 所指向的字符 不是 合法字符时，
            while (leftCharCursor < rightCharCursor && // 条件① 左字符指针 小于 右字符指针
                    !Character.isLetterOrDigit(s.charAt(rightCharCursor))) { // 条件② 右字符指针所指向的字符 不是 字母字符 或 数字字符
                // 把 右字符指针 向左移动（跳过非法字符）
                rightCharCursor--;
            }

            /* （不区分大小写地）比较 左字符指针所指向的字符 与 右字符指针所指向的字符 */
            // 手段：统一转成小写进行比较； Character.toLowerCase(<given_char>)
            char leftChar = Character.toLowerCase(s.charAt(leftCharCursor));
            char rightChar = Character.toLowerCase(s.charAt(rightCharCursor));

            // 比较 两个字符 是否相等     手段：char1 == char2
            // 如果 不相等，说明 字符串不是 回文字符串，
            if (leftChar != rightChar) {
                // 则：返回false
                return false;
            }

            // 如果 字符相等，说明 当前位置检查通过，则：
            // 向内移动指针，继续检查其他字符
            leftCharCursor++;
            rightCharCursor--;
        }

        return true;
    }
}
