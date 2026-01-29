package com.henry.tryout.leetcodes.Huawei.phase2.str.repeated_string_match_686.exe.KMP;

// 计算LPS数组（已匹配子串的 最长公共前后缀 的长度）
// 推荐使用 0-based 的 LPS（Longest Prefix Suffix）数组，避免 人为添加 占位符
public class ConstructLPS {

    /**
     * 计算 给定的模式字符串 的最长公共前后缀的长度
     * @param pattern   模式字符串（标准 0-based 字符串）
     * @return 模式字符串在各个位置上的前缀位置数组
     */
    public int[] computeLPS(String pattern) {
        int patCharAmount = pattern.length();
        int[] currentSpotToItsPrefixSpot = new int[patCharAmount]; // lps[i] = pattern[0..i] 的最长公共真前后缀长度

        // 最长公共前后缀的前缀指针（用于指向 前缀的末尾字符）
        // 初始指在位置0，表示还没有公共前后缀
        int currentPrefixCursor = 0;

        // 模式字符指针（用于指向 当前的模式字符）
        // 初始指在位置1，因为按照定义 单一字符 不存在最长公共前后缀（LPS(0)=0）
        int currentPatCharCursor = 1;

        while (currentPatCharCursor < patCharAmount) {
            /* LPS扩展成功 */
            // 如果 新增的模式字符 与 当前前缀指针 所指向的字符 相同，说明 ‘公共前后缀’扩展成功，则：
            if (pattern.charAt(currentPatCharCursor) == pattern.charAt(currentPrefixCursor)) {
                // ① 把 前缀指针 向后移动一个位置（扩展 公共前后缀）
                currentPrefixCursor++;
                // ② 添加 当前位置 -> 其最长公共前后缀的前缀指针的位置 的映射
                currentSpotToItsPrefixSpot[currentPatCharCursor] = currentPrefixCursor;
                // ③ 把 模式字符指针 也向后移动一个位置（继续检查下一个模式字符）
                currentPatCharCursor++;
            } else { // 否则，说明 公共前后缀扩展失败，则：
                /* LPS扩展失败，回退‘前缀指针’（根据既有的LPS[]数组的指导） */
                // ① 如果 当前前缀指针的位置 还不是 0，说明 还有回退空间，则：
                if (currentPrefixCursor != 0) {
                    // （利用已计算的 lps信息）回退 前缀指针
                    // 手段：回退到 lps[len-1]
                    currentPrefixCursor = currentSpotToItsPrefixSpot[currentPrefixCursor - 1];
                } else { // ② 如果 当前前缀指针的位置 为 0，说明 已经回退到了 起点，则：
                    // 把 当前位置 所对应的前缀指针位置 归零
                    currentSpotToItsPrefixSpot[currentPatCharCursor] = 0;
                    // 把 模式指针 向后移动一个位置（检查下一个模式字符）
                    currentPatCharCursor++;
                }
            }
        }

        // 返回 计算得到的 当前位置 -> 其前缀指针位置 的数组
        return currentSpotToItsPrefixSpot;
    }
}
