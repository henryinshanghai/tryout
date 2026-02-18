package com.henry.tryout.leetcodes.Rakuten.max_product_subArr_152.exe;

public class Solution_qianwen_2Vars {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        // #Q1：初始值应该怎么设置?
        // 准备一个变量   用于维护 ’当前乘积最大的子数组‘的乘积
        // 初始化 ’当前乘积最大的子数组‘ 为 第一个元素
        int currMaxProdSubArrProduct = nums[0];
        // 准备一个变量   用于维护 ’当前乘积最小的子数组‘的乘积
        // 初始化 当前乘积最小的子数组 为 第一个元素
        int currMinProdSubArrProduct = nums[0];
        // 准备一个变量   用于维护 ’乘积最大的子数组‘的乘积
        // 初始化 乘积最大的子数组 为 第一个元素
        int maxProduct = nums[0];

        /* 从第一个元素开始，尝试扩展子数组（来 得到’乘积更大的子数组‘ 以及 ‘乘积更小的子数组’） */
        for (int spotToExtend = 1; spotToExtend < nums.length; spotToExtend++) {
            /* 选择：① 使用当前元素 来 重开子数组；② 把 当前元素 添加到 当前子数组中；*/

            // #Q2: 为什么这里需要使用 临时变量呢?
            // 答：为了保证 max、min的计算 都基于 旧的max、min的值（新值会污染计算过程）；
            int currentNum = nums[spotToExtend];

            /* 计算 ’当前乘积最大的子数组‘的乘积 */
            int tempMax = Math.max(currentNum, // ① 当前位置的数组元素
                    Math.max(currMaxProdSubArrProduct * currentNum, // ②-1 使用当前元素*‘当前乘积最大的子数组’（当前元素为正数）
                            currMinProdSubArrProduct * currentNum)); // ②-2 使用当前元素*‘当前乘积最小的子数组’（当前元素为负数）

            /* 计算 ’当前乘积最小的子数组‘的乘积 */
            int tempMin = Math.min(currentNum, // ① 当前位置的数组元素
                    Math.min(currMaxProdSubArrProduct * currentNum, // ②-1 使用当前元素*‘当前乘积最大的子数组’（当前元素为负数）
                            currMinProdSubArrProduct * currentNum)); // ②-2 使用当前元素*‘当前乘积最小的子数组’（当前元素为正数）

            // 维护 ’当前乘积最大的子数组‘的乘积
            currMaxProdSubArrProduct = tempMax;
            // 维护 ’当前乘积最小的子数组‘的乘积
            currMinProdSubArrProduct = tempMin;

            // 使用 ’当前乘积最大的子数组‘的乘积 来 尝试更新‘最大乘积’
            maxProduct = Math.max(maxProduct, currMaxProdSubArrProduct);
        }

        return maxProduct;
    }
}
