package com.henry.tryout.leetcodes.Huawei.phase3.dynamic_programming.climbing_stairs_70.exe;

public class Solution_qianwen_2Variables {
    public int climbStairs(int targetStairNo) {
        if (targetStairNo <= 2) return targetStairNo;
        int approachAmountReachToPrev2Stair = 1; // f(1)
        int approachAmountReachToPrev1Stair = 2; // f(2)

        int approachAmountReachToCurrStair = 0;

        for (int currentStairNo = 3; currentStairNo <= targetStairNo; currentStairNo++) {
            approachAmountReachToCurrStair = approachAmountReachToPrev1Stair + approachAmountReachToPrev2Stair;

            approachAmountReachToPrev2Stair = approachAmountReachToPrev1Stair;
            approachAmountReachToPrev1Stair = approachAmountReachToCurrStair;
        }


        return approachAmountReachToCurrStair;
    }
}
