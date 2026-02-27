package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.mini_parser_385.exe;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 配合 “简单示例” 更容易理解 代码在做什么
public class Solution_gongshui_stack {
    static NestedInteger sentinelInstance = new NestedIntegerImpl(0);

    public NestedInteger deserialize(String strToParse) {
        // 把 字符串 转化为 字符数组
        char[] charSeqToParse = strToParse.toCharArray();

        int charAmount = charSeqToParse.length;
        // 准备一个字符指针     用于指向当前正在处理的字符
        int currentCharCursor = 0;

        Deque<NestedInteger> NIMemberStack = new ArrayDeque<>();

        /* 把 字符串所表示的嵌套列表 收集到一个 嵌套整数对象（题目提供）中  */
        while (currentCharCursor < charAmount) {
            // 获取到 字符串中的当前字符
            char currentChar = charSeqToParse[currentCharCursor];

            /* 情形〇 当前字符 是 ‘,’（分隔字符） */
            // 如果当前字符为','（一个单纯的分隔符），说明无需任何处理，
            if (currentChar == ',') {
                // 则：移动 字符指针 到下一个位置，并 跳过 后继处理
                ++currentCharCursor;
                continue;
            }

            /* 情形① 当前字符 是 数字相关字符(‘-’ 或 ‘0-9’) */
            // 如果 当前字符是'-' 或者 数字字符，说明 它属于当前实体，
            if (currentChar == '-' || Character.isDigit(currentChar)) {
                /* 则：对 当前实体 进行解析 */
                // ① 准备 ‘当前数字字符指针’
                // 如果 当前字符 是'-'，则 把‘当前数字字符’指针 指向 ‘当前字符指针’的下一个位置
                // 否则，把 ‘当前数字字符’指针 指向 ‘当前字符指针’的位置
                int currentNumCursor =
                        currentChar == '-' ? currentCharCursor + 1 : currentCharCursor;

                // 准备 当前整数的变量
                int parsedNumVal = 0;

                // ② 解析得到 当前整数的值
                // 手段：把 数字字符 逐个累进到 当前整数中
                while (currentNumCursor < charAmount &&
                        Character.isDigit(charSeqToParse[currentNumCursor])) {
                    // 每累进一个新的数字字符，就需要把当前整数*10
                    int currentDigit = charSeqToParse[currentNumCursor++] - '0';
                    parsedNumVal = parsedNumVal * 10 + currentDigit;
                }

                // ③ 把 解析得到的整数 封装进 一个‘嵌套整数实例’，然后 入栈
                NIMemberStack.push(new NestedIntegerImpl(currentChar == '-' ? -parsedNumVal : parsedNumVal));

                // 把 字符游标指针 快进到 当前段的指针处
                currentCharCursor = currentNumCursor;
            } else if (currentChar == '[') { /* 情形② 当前字符 是 ‘[’字符 */
                // 如果 当前字符 是'['，说明 紧跟着一个 新的实体，
                // 则：① 入栈 一个空的‘嵌套整数实例’      用于 存储解析结果
                NIMemberStack.push(new NestedIntegerImpl());
                // ② 再入栈一个 ‘哨兵实例’     用于标记 一个新列表的开始
                NIMemberStack.push(sentinelInstance);

                // 把 字符指针 向后移动一个位置
                currentCharCursor++;
            } else { /* 情形③ 当前字符 是 ‘]’字符 */
                // 说明 当前整数嵌套列表 结束，
                // 则：从栈中 弹出 该嵌套列表列表中 所有的整数，并 把它们收集到 一个list中

                // 准备一个list   用于 收集‘当前整数嵌套列表’中的所有成员
                List<NestedInteger> currNestedListNIMembers = new ArrayList<>();

                // 把 当前嵌套列表中的 所有整数 添加到list中
                while (!NIMemberStack.isEmpty()) {
                    NestedInteger memberOfCurrNestedList = NIMemberStack.pop();
                    // 直到 遇到 栈中的哨兵实例（它的下一个元素就是空对象），
                    // 说明 当前列表的实体 全部出栈（全部添加到list中），
                    if (memberOfCurrNestedList == sentinelInstance) {
                        // 则：停止收集
                        break;
                    }

                    // 把 当前嵌套列表中的当前成员 添加到list中
                    currNestedListNIMembers.add(memberOfCurrNestedList);
                } /* 循环结束后，① list中 包含了 当前列表的所有实体；② 栈顶元素是一个 空的嵌套实例 */

                /* 把 当前列表中的所有成员，按照正确的顺序（逆序） 追加到 栈顶的空实例中 */
                for (int currNIMemberCursor = currNestedListNIMembers.size() - 1;
                     currNIMemberCursor >= 0; currNIMemberCursor--) {
                    NestedInteger currentNIMember = currNestedListNIMembers.get(currNIMemberCursor);
                    NIMemberStack.peek().add(currentNIMember);
                } /* 循环结束后，栈顶的空实例[] 变成了 [123, 456] */

                // 处理完 因为']'所需要处理的所有实体 后，把 字符指针 向后移动一个位置
                currentCharCursor++;
            }
        }

        // 解析完成后，栈中只剩下一个元素 - 栈顶元素 就是 预期结果
        return NIMemberStack.peek();
    }

    private boolean isDigitChar(char charSeqToParse) {
        return charSeqToParse >= '0' &&
                charSeqToParse <= '9';
    }
}
