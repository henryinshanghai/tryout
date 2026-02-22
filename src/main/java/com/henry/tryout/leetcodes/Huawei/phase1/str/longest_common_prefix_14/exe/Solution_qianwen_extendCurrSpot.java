package com.henry.tryout.leetcodes.Huawei.phase1.str.longest_common_prefix_14.exe;

// 概念：
// ① 基准字符串；② 待比较字符串；③ 待扩展位置；
// 手段：扩展当前位置 来 得到公共前缀
public class Solution_qianwen_extendCurrSpot {
    public String longestCommonPrefix(String[] strs) {
        /* 边界检查 —— 如果 数组为空 或 没有字符串，返回 空串 */
        if (strs == null || strs.length == 0) {
            return "";
        }

        // ① 选定第一个字符串 为 基准字符串
        String pivotStr = strs[0];

        // ② 遍历 基准字符串中的 每一个字符位置
        for (int currentSpotToExtend = 0; currentSpotToExtend < pivotStr.length(); currentSpotToExtend++) {
            // 获取 基准字符串 在当前位置上的字符
            char pivotCharOnCurrSpot = pivotStr.charAt(currentSpotToExtend);

            /* ③ 尝试 使用该位置 来 扩展 公共前缀 */
            // 手段：对于 当前位置，逐个比较 其他字符串 与 基准字符串 在此位置上的字符；
            for (int currentStrCursor = 1; currentStrCursor < strs.length; currentStrCursor++) {
                String currentStrToCompare = strs[currentStrCursor];

                // 扩展公共前缀 的过程 结束（如遇 以下情形）👇
                if (currentSpotToExtend >= currentStrToCompare.length() // 情形1：‘当前待比较字符串’中的字符 已经用尽；
                        || currentStrToCompare.charAt(currentSpotToExtend) != pivotCharOnCurrSpot) { // 情形2：‘当前待比较字符串’ 当前位置上的字符 发生了失配
                    // 则：返回 当前扩展得到的 公共前缀（它就是最长公共前缀）
                    // 手段：截取子字符串[0, 扩展位置)
                    return pivotStr.substring(0, currentSpotToExtend);
                }
            } /* 当前位置扩展成功，继续尝试 下一个位置 */
        }

        // ④ 如果 所有字符 都匹配完，
        // 说明 基准字符串 就是 最长公共前缀
        return pivotStr;
    }
}
