package com.henry.tryout.leetcodes.Rakuten.hash_a_str_3271.exe;

public class Solution_qianwen_substring {
    public String stringHash(String charSeq, int segmentSize) {
        int charAmount = charSeq.length();

        StringBuilder codedResult = new StringBuilder();

        for (int currentSpot = 0; currentSpot < charAmount; currentSpot += segmentSize) {
            String currentSegmentStr = charSeq.substring(currentSpot, currentSpot + segmentSize);

            int currentSegmentHashSum = 0;
            for (char currentCharInSegment : currentSegmentStr.toCharArray()) {
                // 获得 字符 在字母表中的下标   手段：字符 - 'a'
                int currentCharsHash = currentCharInSegment - 'a';
                currentSegmentHashSum += currentCharsHash;
            }

            // 获取到 字母表中 指定位置上的字符    手段：(char)('a' + 指定位置(整数))
            int offset = currentSegmentHashSum % 26;
            char encodedChar = (char) ('a' + offset);
            codedResult.append(encodedChar);
        }

        return codedResult.toString();
    }
}
