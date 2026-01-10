package com.henry.tryout.leetcodes.Rakuten.longest_mountain_in_arr_845.exe;

// 中心扩展法
public class Solution_qianwen_2cursors {
    public int longestMountain(int[] itemArr) {
        int itemAmount = itemArr.length;
        if (itemAmount < 3)
            return 0;

        int longestMountainLength = 0;
        int currentSpot = 1; // 从索引 1 开始，因为峰顶不能在两端

        while (currentSpot < itemAmount - 1) {
            // 判断 当前位置 是否为 潜在峰顶：左边小，右边小
            if (isPotentialPeakSpot(itemArr, currentSpot)) {
                int itsLeftSpot = currentSpot - 1;
                int itsRightSpot = currentSpot + 1;

                // 向左扩展指针 直到条件不满足：找 最长严格递增段
                while (itsLeftSpot > 0 && biggerThanLeft(itemArr, itsLeftSpot)) {
                    itsLeftSpot--;
                }

                // 向右扩展 直到条件不满足：找最长严格递减段
                while (itsRightSpot < itemAmount - 1 && biggerThanRight(itemArr, itsRightSpot)) {
                    itsRightSpot++;
                }

                // 更新山脉的最大长度
                longestMountainLength = Math.max(longestMountainLength, itsRightSpot - itsLeftSpot + 1);

                // 跳过 已处理的山脉部分，避免 重复检查
                currentSpot = itsRightSpot;
            } else { // 否则，直接跳到下一个位置 对它进行检查
                currentSpot++;
            }
        }

        return longestMountainLength;
    }

    private boolean isPotentialPeakSpot(int[] itemArr, int currentSpot) {
        return biggerThanLeft(itemArr, currentSpot) && biggerThanRight(itemArr, currentSpot);
    }

    private boolean biggerThanRight(int[] itemArr, int currentSpot) {
        return itemArr[currentSpot] > itemArr[currentSpot + 1];
    }

    private boolean biggerThanLeft(int[] itemArr, int currentSpot) {
        return itemArr[currentSpot - 1] < itemArr[currentSpot];
    }
}
