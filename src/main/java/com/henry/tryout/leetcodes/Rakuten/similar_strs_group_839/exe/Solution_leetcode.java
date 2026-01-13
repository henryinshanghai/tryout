package com.henry.tryout.leetcodes.Rakuten.similar_strs_group_839.exe;

public class Solution_leetcode {

    int[] currentNodeToItsParentNode;

    public int numSimilarGroups(String[] strs) {
        int strAmount = strs.length;
        int strLength = strs[0].length();

        currentNodeToItsParentNode = new int[strAmount];
        for (int currentNode = 0; currentNode < strAmount; currentNode++) {
            currentNodeToItsParentNode[currentNode] = currentNode;
        }

        for (int currentSpotI = 0; currentSpotI < strAmount; currentSpotI++) {
            for (int currentSpotJ = currentSpotI + 1; currentSpotJ < strAmount; currentSpotJ++) {

                /*
                    💡 为什么先 find 再 check？
                    因为如果两个字符串 已经通过 其他路径 连通（比如 A～B, B～C ⇒ A 和 C 已同组），即使 A 和 C 不直接相似，也不需要再处理。
                    提前跳过 可以显著减少 不必要的 字符串比较！
                 */
                int rootOfNode1 = findRootOf(currentSpotI),
                    rootOfNode2 = findRootOf(currentSpotJ);

                if (rootOfNode1 == rootOfNode2) {
                    continue;
                }

                if (AreSimilar(strs[currentSpotI], strs[currentSpotJ], strLength)) {
                    currentNodeToItsParentNode[rootOfNode1] = rootOfNode2;
                }
            }
        }

        int groupAmount = 0;
        for (int currentSpot = 0; currentSpot < strAmount; currentSpot++) {

            if (currentNodeToItsParentNode[currentSpot] == currentSpot) {
                groupAmount++;
            }
        }
        return groupAmount;
    }

    public int findRootOf(int currentNode) {
        // 带 路径压缩 的递归实现
        return currentNodeToItsParentNode[currentNode] == currentNode
                ? currentNode
                : (currentNodeToItsParentNode[currentNode] = findRootOf(currentNodeToItsParentNode[currentNode]));
    }

    /**
     * 判断 两个字母异位词 是否相似
     * @param str1  字母异位词1
     * @param str2  字母异位词2
     * @param strLength 单词长度
     * @return
     */
    public boolean AreSimilar(String str1, String str2, int strLength) {
        int diffSpotAmount = 0;
        for (int currentSpot = 0; currentSpot < strLength; currentSpot++) {

            if (str1.charAt(currentSpot) != str2.charAt(currentSpot)) {
                diffSpotAmount++;

                // 如果 存在 超过两个位置上的字母不同，说明 不可能通过一次交换 使两个单词相同，则：
                if (diffSpotAmount > 2) {
                    // 不相似
                    return false;
                }
            }
        }

        return true;
    }
}
