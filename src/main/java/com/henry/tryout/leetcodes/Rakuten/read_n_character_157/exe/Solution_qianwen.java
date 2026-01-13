package com.henry.tryout.leetcodes.Rakuten.read_n_character_157.exe;

public class Solution_qianwen extends Reader4 {

    /**
     * 从文件中 读取 指定数量的字符，并 把读取到的字符 写入到 指定的目标数组中
     * 🐖 题目限制：只能使用 特定的API read4()来读取 - 一次固定读取 4个字符(或 更少，如果遇到EOF)
     *
     * @param targetCharArr     指定的目标字符数组
     * @param wantedCharAmount  指定的数量
     * @return  写入到 指定目标数组中的 字符数量
     *
     * 🐖 受限于 文件中 具体的字符数量，返回的字符数量 可能会 小于 wantedCharAmount
     * 比如 文件中只有3个字符，但 调用者 想要 读取4个字符
     */
    public int read(char[] targetCharArr, int wantedCharAmount) {
        char[] tempBufferCharArr = new char[4]; // 供 read4 使用的 临时缓冲区
        int totalReadCharAmount = 0;             // 已读取的总字符数

        while (totalReadCharAmount < wantedCharAmount) {
            /* 每次 读取文件内容后，都需要 马上判断 文件是否已经结束（否则 while会无限循环） */
            // 读取(最多)4个字符 到 （自定义）缓冲区中
            int currentReadCharAmount = read4(tempBufferCharArr);
            // 如果 读取到的字符数量 为 0，说明 已经读到了文件结束，则：
            if (currentReadCharAmount == 0) {
                // 跳出循环
                break;
            }

            // 本次 最多能写入到 目标字符数组 的字符数量
            // 原则：从temp 向buff中 写入的字符数量 不能超过 buff所需要的字符数量
            int charsAmountWriteToTarget =
                    Math.min(currentReadCharAmount, // 当前次 使用read4() 所读取到的 字符数量
                            wantedCharAmount - totalReadCharAmount); // buff 当前所需要的字符数量

            // 把 temp中的字符 按需复制到 目标buf中
            for (int currentCharCursor = 0; currentCharCursor < charsAmountWriteToTarget; currentCharCursor++) {
                targetCharArr[totalReadCharAmount + currentCharCursor] = tempBufferCharArr[currentCharCursor];
            }

            // 字符复制完成后，更新 当前buff中的字符数量
            totalReadCharAmount += charsAmountWriteToTarget;

            // (推荐做法：提前退出，意图明确)
            // 如果 目标buff中的字符数量 等于 调用者所期待的字符数量，说明 读取过程完成，
            if (totalReadCharAmount == wantedCharAmount) {
                // 则：跳出循环，不再继续读取
                break;
            }
        }

        // 最终，返回 写入到buff中的 字符数量
        return totalReadCharAmount;
    }
}

class Reader4 {
    int read4(char[] temp) {
        return 100;
    }
}
