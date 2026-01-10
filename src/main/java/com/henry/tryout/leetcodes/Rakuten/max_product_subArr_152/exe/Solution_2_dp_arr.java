package com.henry.tryout.leetcodes.Rakuten.max_product_subArr_152.exe;

public class Solution_2_dp_arr {
    public int maxProduct(int[] nums) {
        int numAmount = nums.length;

        int[] currentSpotToMaxProductReachIt = new int[numAmount];
        int[] currentSpotToMinProductReachIt = new int[numAmount];

        currentSpotToMaxProductReachIt[0] = nums[0];
        currentSpotToMinProductReachIt[0] = nums[0];

        int maxProduct = nums[0];

        for (int currentSpot = 1; currentSpot < nums.length; currentSpot++) {
            int currentNum = nums[currentSpot];
            if (currentNum < 0) {
                int temp = currentSpotToMaxProductReachIt[currentSpot - 1];
                currentSpotToMaxProductReachIt[currentSpot - 1] = currentSpotToMinProductReachIt[currentSpot - 1];
                currentSpotToMinProductReachIt[currentSpot - 1] = temp;
            }

            currentSpotToMaxProductReachIt[currentSpot] = Math.max(
                    currentSpotToMaxProductReachIt[currentSpot - 1] * currentNum, // option1
                    currentNum// option2
            );

            currentSpotToMinProductReachIt[currentSpot] = Math.min(
                    currentSpotToMinProductReachIt[currentSpot - 1] * currentNum,
                    currentNum
            );

            maxProduct = Math.max(maxProduct, currentSpotToMaxProductReachIt[currentSpot]);
        }

        return maxProduct;
    }
}
