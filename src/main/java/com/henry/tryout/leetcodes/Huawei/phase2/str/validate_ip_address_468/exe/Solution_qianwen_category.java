package com.henry.tryout.leetcodes.Huawei.phase2.str.validate_ip_address_468.exe;

// “IP 验证 = 字符合法性 + 格式结构 + 数值范围 + 边界覆盖”
public class Solution_qianwen_category {
    public String validIPAddress(String queryIP) {
        if (queryIP.contains(".") && queryIP.contains(":")) {
            return "Neither"; // 不能同时含 . 和 :
        }

        if (queryIP.contains(".")) {
            return validateIPv4(queryIP) ? "IPv4" : "Neither";
        } else if (queryIP.contains(":")) {
            return validateIPv6(queryIP) ? "IPv6" : "Neither";
        } else {
            return "Neither";
        }
    }

    private boolean validateIPv4(String ipStr) {
        /* 把字符串 以.字符作为分隔符 进行分割（如果末尾存在有空字符串，则 保留它） */
        // 手段：调用 split(<separator>, <limit>)时，第二个参数 传入-1
        String[] segmentArr = ipStr.split("\\.", -1); // -1 保留尾部空串

        // 所有段的总数量约束
        if (segmentArr.length != 4) {
            return false;
        }

        for (String currentSegment : segmentArr) {
            if (!isValidIPv4Segment(currentSegment)) {
                return false;
            }
        }
        return true;
    }

    /* IPv4每一段所需要遵守的约束 */
    private boolean isValidIPv4Segment(String passedStr) {
        // ① 非空约束
        if (passedStr == null || passedStr.length() == 0) return false;
        // ② 段的长度约束
        if (passedStr.length() > 3) return false;

        // ③ 段的字符性质约束（是否 所有位全为数字）
        for (char currentChar : passedStr.toCharArray()) {
            if (!Character.isDigit(currentChar)) {
                return false;
            }
        }

        // ④ 前导零约束（"0" 允许，"01" 不允许）
        if (passedStr.charAt(0) == '0' && passedStr.length() > 1) {
            return false;
        }

        // ⑤ 数值范围约束（检查 当前片段的 取值 是否∈[0， 255]）
        int num = Integer.parseInt(passedStr);
        return num >= 0 && num <= 255;
    }

    // 判断 整个字符串 是否合法
    private boolean validateIPv6(String ipStr) {
        /* 对 整个IPv6字符串 进行 保留尾部空串的分割 */
        String[] segmentArr = ipStr.split(":", -1); // -1 保留尾部空串
        // 所有段的总数量约束
        if (segmentArr.length != 8) return false;

        for (String currentPart : segmentArr) {
            if (!isValidIPv6Segment(currentPart)) {
                return false;
            }
        }
        return true;
    }

    // 判断 片段字符串 是否合法
    private boolean isValidIPv6Segment(String passedStr) {
        // 非空约束
        if (passedStr == null || passedStr.length() == 0) return false;
        // 字符长度约束
        if (passedStr.length() > 4) return false;

        // 片段的字符约束（十六进制字符）
        for (char currentChar : passedStr.toCharArray()) {
            if (!isHexChar(currentChar)) {
                return false;
            }
        }
        return true;
    }

    // 判断一个字符 是不是 十六进制数的字符
    private boolean isHexChar(char passedChar) {
        return (passedChar >= '0' && passedChar <= '9') || // [0-9]
                (passedChar >= 'a' && passedChar <= 'f') || // [a-f]
                (passedChar >= 'A' && passedChar <= 'F'); // [A-F]
    }
}
