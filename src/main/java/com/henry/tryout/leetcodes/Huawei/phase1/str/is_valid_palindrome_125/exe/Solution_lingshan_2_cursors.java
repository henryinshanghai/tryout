package com.henry.tryout.leetcodes.Huawei.phase1.str.is_valid_palindrome_125.exe;

// 按需移动指针，在必要时比较
public class Solution_lingshan_2_cursors {
    public boolean isPalindrome(String s) {
        int leftCharCursor = 0;
        int rightCharCursor = s.length() - 1;

        while (leftCharCursor < rightCharCursor) {
            if (!Character.isLetterOrDigit(s.charAt(leftCharCursor))) {
                leftCharCursor++;
            } else if (!Character.isLetterOrDigit(s.charAt(rightCharCursor))) {
                rightCharCursor--;
            } else if (Character.toLowerCase(s.charAt(leftCharCursor)) == Character.toLowerCase(s.charAt(rightCharCursor))) {
                leftCharCursor++;
                rightCharCursor--;
            } else {
                return false;
            }
        }
        return true;
    }
}
