package com.henry.tryout.leetcodes.Huawei.phase1.str.longest_common_prefix_14.exe;

public class Solution_qianwen_returnWhenExpandFinish {
    public String longestCommonPrefix(String[] strs) {
        /* 边界检查 —— 如果 数组为空 或 没有字符串，返回 空串 */
        if (strs == null || strs.length == 0) {
            return "";
        }

        String pivotStr = strs[0];

        // 遍历 字符位置
        for (int currentCharSpot = 0; currentCharSpot < pivotStr.length(); currentCharSpot++) {
            // 获取 基准字符串 在当前位置上的字符
            char pivotCharOnCurrSpot = pivotStr.charAt(currentCharSpot);

            // 尝试 使用当前位置 来 扩展 公共前缀
            // 手段：对于 当前位置，逐个比较 其他字符串 与 基准字符串 在此位置上的字符；
            for (int currentStrSpot = 1; currentStrSpot < strs.length; currentStrSpot++) {
                String currentStr = strs[currentStrSpot];

                // 如果 遇到以下情形，说明 扩展公共前缀 的过程 结束，则：
                if (currentCharSpot >= currentStr.length() // 情形1：当前字符串中的字符 已经用尽；
                        || currentStr.charAt(currentCharSpot) != pivotCharOnCurrSpot) { // 情形2：当前字符串 当前位置上的字符 发生了失配
                    // 返回 当前扩展得到的 公共前缀（它就是最长公共前缀）    手段：截取子字符串(左闭右开)
                    return pivotStr.substring(0, currentCharSpot);
                }
            }
        }

        // 步骤4: 如果 所有字符 都匹配完，说明 第一个字符串 就是 最长公共前缀
        return pivotStr;
    }
}
