package com.henry.tryout.leetcodes.Rakuten.longest_mountain_in_arr_845.exe;

// 维护两个DP数组，初始化、递推公式都比较容易写错
public class Solution_qianwen_climbingAndDownhillArr {
    public int longestMountain(int[] itemArr) {
        int itemAmount = itemArr.length;
        if (itemAmount < 3) return 0;

        // currentSpotToMaxClimbingLengthReachIt[i]：以 i 结尾的最长严格递增序列长度
        int[] currentSpotToMaxClimbingLengthReachIt = new int[itemAmount];
        // currentSpotToMaxDownhillLengthFromIt[i]：以 i 开头的最长严格递减序列长度
        int[] currentSpotToMaxDownhillLengthFromIt = new int[itemAmount];

        // 填充 currentSpotToMaxClimbingLengthReachIt 数组（从左到右）
        currentSpotToMaxClimbingLengthReachIt[0] = 1;
        for (int currentSpot = 1; currentSpot < itemAmount; currentSpot++) {
            // 如果 当前元素 大于 它的前一个元素，说明 当前是 爬升的，
            if (itemArr[currentSpot] > itemArr[currentSpot - 1]) {
                // 则：到达 当前位置的 最大爬升距离 = 到达 上一个位置的 最大爬升距离 + 1
                currentSpotToMaxClimbingLengthReachIt[currentSpot] =
                        currentSpotToMaxClimbingLengthReachIt[currentSpot - 1] + 1;
            } else { // 否则，说明 不是爬升状态(下降 或 持平)，
                // 则：归1，重新开始 累计爬升距离
                currentSpotToMaxClimbingLengthReachIt[currentSpot] = 1;
            }
        }

        // 填充 currentSpotToMaxDownhillLengthFromIt 数组（从右到左）
        currentSpotToMaxDownhillLengthFromIt[itemAmount - 1] = 1;
        for (int currentSpot = itemAmount - 2; currentSpot >= 0; currentSpot--) {
            // 如果 当前元素 比起它的下一个元素 更大，说明 是下降状态，则：
            if (itemArr[currentSpot] > itemArr[currentSpot + 1]) {
                // 从 当前位置 开始的下降距离 = 从 当前位置的下一个位置 开始的下降距离 + 1
                currentSpotToMaxDownhillLengthFromIt[currentSpot]
                        = currentSpotToMaxDownhillLengthFromIt[currentSpot + 1] + 1;
            } else { // 否则，说明 不是下降状态(上升 或 持平)，
                // 则：归1，重新开始 累计下降距离
                currentSpotToMaxDownhillLengthFromIt[currentSpot] = 1;
            }
        }

        int maxLen = 0;
        // 检查 每个位置 是否可以 作为峰顶
        for (int currentSpot = 0; currentSpot < itemAmount; currentSpot++) {
            // 必须有上升段（currentSpotToMaxClimbingLengthReachIt[currentSpot] > 1）和下降段（currentSpotToMaxDownhillLengthFromIt[currentSpot] > 1）
            int maxClimbingLengthReachIt = currentSpotToMaxClimbingLengthReachIt[currentSpot];
            int maxDownhillLengthFromIt = currentSpotToMaxDownhillLengthFromIt[currentSpot];

            if (maxClimbingLengthReachIt > 1 && maxDownhillLengthFromIt > 1) {
                maxLen = Math.max(maxLen, maxClimbingLengthReachIt + maxDownhillLengthFromIt - 1);
            }
        }

        return maxLen >= 3 ? maxLen : 0;
    }
}
