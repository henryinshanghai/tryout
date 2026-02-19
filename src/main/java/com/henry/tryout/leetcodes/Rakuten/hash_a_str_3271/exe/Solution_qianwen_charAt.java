package com.henry.tryout.leetcodes.Rakuten.hash_a_str_3271.exe;

// 使用两个指针，一个 指向 当前子字符串的起始位置，另一个 遍历 当前子字符串的当前字符
public class Solution_qianwen_charAt {
    public String stringHash(String charSeq, int segmentSize) {
        int charAmount = charSeq.length();

        StringBuilder encodedResult = new StringBuilder();

        for (int currentSpotInOriginalArr = 0; currentSpotInOriginalArr < charAmount; currentSpotInOriginalArr += segmentSize) {
            int currentSegmentHash = 0;
            for (int currentSpotInSegment = 0; currentSpotInSegment < segmentSize; currentSpotInSegment++) {
                // 获取到 当前位置上的字符
                int currentCharSpot = currentSpotInOriginalArr + currentSpotInSegment;
                char currentChar = charSeq.charAt(currentCharSpot);
                // 获取到 该字符 在字母表中的下标     手段：字符 - 'a'
                int currentCharHash = currentChar - 'a';

                // 把 该字符的hash结果 累加到 segment的hash结果中
                currentSegmentHash += currentCharHash;
            }

            // 计算 segment对26取余的结果
            int currentSegmentsOffset = currentSegmentHash % 26;
            // 使用该结果 来 作为字母表的下标 来 编码segment     手段：(char)('a' + offset)
            char encodedChar = (char) ('a' + currentSegmentsOffset);
            // 把 该segment的编码字符 添加到 最终的编码字符串中
            encodedResult.append(encodedChar);
        }

        return encodedResult.toString();
    }
}
