package com.henry.tryout.leetcodes.Rakuten.two_sum_01.exe;

import java.util.HashMap;
import java.util.Map;

public class Solution_qianwen {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToItsSpotMap = new HashMap();

        for (int currentSpot = 0; currentSpot < nums.length; currentSpot++) {
            int currentNum = nums[currentSpot];
            int expectedNum = target - currentNum;

            if (numToItsSpotMap.containsKey(expectedNum)) {
                int partnerNumSpot = numToItsSpotMap.get(expectedNum);
                return new int[]{currentSpot, partnerNumSpot};
            }

            numToItsSpotMap.put(currentNum, currentSpot);
        }

        return new int[]{-1, -1};
    }
}
