package com.henry.tryout.leetcodes.Huawei.phase3.DFS_and_BFS.mini_parser_385.exe;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution_gongshui_stack {
    static NestedInteger markInstance = new NestedIntegerImpl(0);

    public NestedInteger deserialize(String strToParse) {

        char[] charSeqToParse = strToParse.toCharArray();
        int charAmount = charSeqToParse.length,
                currentCharCursor = 0;

        Deque<NestedInteger> segmentStack = new ArrayDeque<>();

        while (currentCharCursor < charAmount) {
            char currentChar = charSeqToParse[currentCharCursor];
            // 如果当前字符为','，说明 无需任何处理，
            if (currentChar == ',') {
                // 则：移动 字符指针 到下一个位置，并 跳过 后继处理
                ++currentCharCursor;
                continue;
            }

            // 如果 当前字符是'-' 或者 数字字符，说明 它属于当前实体，
            if (currentChar == '-' || Character.isDigit(currentChar)) {
                // 则：对 当前实体 进行解析
                // ① 处理'-'字符
                int currentNumCursor = currentChar == '-' ? currentCharCursor + 1 : currentCharCursor,
                        currentNum = 0;
                // ② 解析 数字字符的数值
                while (currentNumCursor < charAmount &&
                        Character.isDigit(charSeqToParse[currentNumCursor])) {
                    currentNum = currentNum * 10 + (charSeqToParse[currentNumCursor++] - '0');
                }
                // ③ 把 解析得到的结果 封装进 嵌套实例，然后 入栈
                segmentStack.push(new NestedIntegerImpl(currentChar == '-' ? -currentNum : currentNum));

                // 把 字符游标指针 快进到 当前段的指针处
                currentCharCursor = currentNumCursor;
            } else if (currentChar == '[') { // 如果 当前字符 是'['，说明 紧跟着一个 新的实体，
                // 则：① 入栈 一个空的嵌套实例      用作列表的容器
                segmentStack.push(new NestedIntegerImpl());
                // ② 再入栈一个 哨兵实例     用于标记 一个新列表的开始
                segmentStack.push(markInstance);

                // 把 字符指针 向后移动一个位置
                currentCharCursor++;
            } else { // 否则，当前字符是']'，说明 当前列表 结束，
                // 则：从栈中 弹出该列表中所有的实体，并 把它们收集到 一个list中
                List<NestedInteger> segmentList = new ArrayList<>();

                while (!segmentStack.isEmpty()) {
                    NestedInteger lastSegmentBelongToCurrentList = segmentStack.pop();
                    // 直到 遇到 栈中的哨兵实例，说明 当前列表的实体 用尽，则：停止收集
                    if (lastSegmentBelongToCurrentList == markInstance) {
                        break;
                    }
                    segmentList.add(lastSegmentBelongToCurrentList);
                } /* 循环结束后，① list中包含当前列表的所有实体；② 栈顶元素是一个 空的嵌套实例 */

                // 把 当前列表中的所有实体，按照正确的顺序（逆序） 追加到 栈顶的空实例中
                for (int currentSegmentCursor = segmentList.size() - 1; currentSegmentCursor >= 0; currentSegmentCursor--) {
                    NestedInteger currentSegment = segmentList.get(currentSegmentCursor);
                    segmentStack.peek().add(currentSegment);
                } /* 循环结束后，栈顶的空实例 变成了 [123, 456] */

                // 处理完 因为']'所需要处理的所有实体后，把 字符指针 向后移动一个位置
                currentCharCursor++;
            }
        }

        // 解析完成后，栈中只剩下一个元素 - 栈顶元素 就是 预期结果
        return segmentStack.peek();
    }

    private boolean isDigitChar(char charSeqToParse) {
        return charSeqToParse >= '0' &&
                charSeqToParse <= '9';
    }
}
