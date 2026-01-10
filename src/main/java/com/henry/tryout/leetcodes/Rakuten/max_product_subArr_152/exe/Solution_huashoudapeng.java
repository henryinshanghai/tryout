package com.henry.tryout.leetcodes.Rakuten.max_product_subArr_152.exe;

public class Solution_huashoudapeng {
    public int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE,
                currentMaxProduct = 1,
                currentMinProduct = 1;

        for (int currentSpot = 0; currentSpot < nums.length; currentSpot++) {
            // 如果 当前的数组元素 是一个负数，说明 在把它乘进去 maxProduct之前 需要先交换 maxProduct与minProduct 以得到最大乘积，则：
            if (nums[currentSpot] < 0) {
                // 交换 currentMaxProduct 与 currentMinProduct
                int tmp = currentMaxProduct;
                currentMaxProduct = currentMinProduct;
                currentMinProduct = tmp;
            }

            // 同时维护 最大乘积 与 最小乘积
            currentMaxProduct = Math.max(currentMaxProduct * nums[currentSpot], nums[currentSpot]);
            currentMinProduct = Math.min(currentMinProduct * nums[currentSpot], nums[currentSpot]);

            maxProduct = Math.max(maxProduct, currentMaxProduct);
        }

        return maxProduct;
    }
}
