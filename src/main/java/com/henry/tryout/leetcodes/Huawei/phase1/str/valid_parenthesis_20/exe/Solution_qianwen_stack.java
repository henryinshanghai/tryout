package com.henry.tryout.leetcodes.Huawei.phase1.str.valid_parenthesis_20.exe;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution_qianwen_stack {
    public boolean isValid(String parenthesisSeq) {
        // 优化：奇数长度一定无效
        if (parenthesisSeq.length() % 2 == 1) {
            return false;
        }

        // 准备 括号匹配的 映射关系：右括号 → 左括号
        Map<Character, Character> rightToItsMatchedLeft = new HashMap();
        rightToItsMatchedLeft.put(')', '(');
        rightToItsMatchedLeft.put('}', '{');
        rightToItsMatchedLeft.put(']', '[');

        Deque<Character> leftParenthesisStack = new ArrayDeque<>();

        /* 遍历 括号字符串，
        如果 当前括号字符 是 某种类型的左括号字符，则 收集到栈中；
        如果 当前括号字符 是 某种类型的右括号字符，则 检查 栈顶 是不是 与其匹配的左括号字符 */
        for (char currentParenthesisChar : parenthesisSeq.toCharArray()) {
            // 如果 map中的key中 包含有 该括号字符，说明 当前字符 是 某种类型的右括号字符，
            if (rightToItsMatchedLeft.containsKey(currentParenthesisChar)) {
                // 则：检查 上一个左括号 是否 与之相匹配
                // 手段：获取 与其相匹配的 左括号字符；获取 上一个左括号字符；比较 这两个字符 是否相同
                // 如果 不相同（或者 栈为空），说明 当前右括号 失配，
                if (leftParenthesisStack.isEmpty() || // 情形1：栈空了
                        leftParenthesisStack.peek() != rightToItsMatchedLeft.get(currentParenthesisChar)) { // 情形2：上一个左括号字符 不是 当前右括号的匹配字符
                    // 🐖 Character之间的比较，使用 != 时 会 自动拆箱为 char之间的比较 👆
                    // 则：字符串是 无效字符串，返回false
                    return false;
                }

                // 否则，说明 当前右括号 匹配成功，则：
                // 把 与其匹配的 栈顶的左括号字符 弹出（这样 下一次遇到右括号时，才能正确地 进行检查）
                leftParenthesisStack.pop();
            } else {
                // 否则，说明 当前字符是 某种类型的左括号，则：把 该字符 入栈
                leftParenthesisStack.push(currentParenthesisChar);
            }
        }

        // 最终 栈 必须为 空
        return leftParenthesisStack.isEmpty();
    }
}
