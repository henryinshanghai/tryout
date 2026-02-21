package com.henry.tryout.leetcodes.Rakuten.read_n_characters_158.exe;

public class Solution_qianwen_internalBuffArr extends Reader4 {
    // 原理：成员变量 属于 实例对象，因此 能够 在多次调用之间 保持状态

    // 作为 内部缓冲区的数组 - 用于 ① 暂存 读取到的字符；② 平衡 字符的供需不一致问题
    private char[] internalCharArrBuffer = new char[4];
    // 缓冲数组的 字符指针    用于指向 当前待处理的字符
    private int currentCharCursor = 0;
    // 缓冲数组中的 字符数量  用于 ① 与字符指针一起 判断 缓冲数组中的字符 是否用尽；② 判断是否到达EOF
    private int buffersCharAmount = 0;

    /**
     * 从文件中 读取指定数量的字符，并存储到 指定的字符数组中
     *
     * @param wantedCharAmount 指定的数量
     * @param targetCharArr    指定的字符数组
     * @return 实际写入到的字符数量
     */
    public int read(int wantedCharAmount, char[] targetCharArr) {
        // buf 的写入索引
        int totalReadCharAmount = 0;

        while (totalReadCharAmount < wantedCharAmount) {
            // ① 如果（多次调用导致）内部缓冲区中的字符 用完了，说明 需要再次 获取一批字符，
            if (requireMoreChars()) {
                // 则：调用 read4 读取多个字符(最多4个) 到 缓冲区中
                // 🐖 使用 读取到的字符数量 来 更新 当前缓冲区字符数组中的字符数量
                buffersCharAmount = read4(internalCharArrBuffer);

                // 把 字符缓冲数组的 字符指针 归零（来 支持 从缓冲数组 拷贝字符到目标数组 的操作）
                // 🐖 对于leetcode的正确性而言，归零操作 需要 在 文件结束操作 之前 - 这样 能够得到 空字符串(符合预期)，而不是"\u0000"（不符合预期）
                currentCharCursor = 0;

                /* 判断 文件 是否结束 */
                // 如果是，说明 读取过程 被强制结束，则：
                if (reachEOF()) {
                    // 跳出循环，读取过程结束
                    break;
                }
            }

            // ② 把 内部字符缓冲数组的 当前字符指针 ‘所指向的字符’ 拷贝到 目标字符数组的‘对应位置’ 中
            targetCharArr[totalReadCharAmount] = internalCharArrBuffer[currentCharCursor];

            /* ③ （拷贝完成后）按需移动指针 */
            // 把 字符缓冲数组中的字符指针 向后移动一个位置
            currentCharCursor++;
            // 累计 统共已经写入到目标数组中的 字符数量
            totalReadCharAmount++;
        }

        // 最终返回 写入到 外部数组中的 字符数量
        return totalReadCharAmount;
    }

    // 是否到达 文件末尾
    private boolean reachEOF() {
        return buffersCharAmount == 0;
    }

    // 是否需要 读取更多字符
    private boolean requireMoreChars() {
        return currentCharCursor == buffersCharAmount;
    }
}

// 黑盒API
class Reader4 {
    // dummy实现
    int read4(char[] buf4) {
        return 100;
    }
}