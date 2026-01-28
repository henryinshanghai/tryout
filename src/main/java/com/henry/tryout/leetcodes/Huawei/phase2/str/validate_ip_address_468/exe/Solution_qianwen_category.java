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
        String[] parts = ipStr.split("\\.", -1); // -1 保留尾部空串
        if (parts.length != 4) return false;

        for (String currentPart : parts) {
            if (!isValidIPv4Part(currentPart)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidIPv4Part(String partStr) {
        if (partStr == null || partStr.length() == 0) return false;
        if (partStr.length() > 3) return false;

        // 检查是否 全为数字
        for (char currentChar : partStr.toCharArray()) {
            if (!Character.isDigit(currentChar)) {
                return false;
            }
        }

        // 检查 前导零（"0" 允许，"01" 不允许）
        if (partStr.charAt(0) == '0' && partStr.length() > 1) {
            return false;
        }

        // 检查 当前片段的 取值 是否在合法范围
        int num = Integer.parseInt(partStr);
        return num >= 0 && num <= 255;
    }

    // 判断 整个字符串 是否合法
    private boolean validateIPv6(String ipStr) {
        String[] parts = ipStr.split(":", -1); // -1 保留尾部空串
        if (parts.length != 8) return false;

        for (String currentPart : parts) {
            if (!isValidIPv6Part(currentPart)) {
                return false;
            }
        }
        return true;
    }

    // 判断 片段字符串 是否合法
    private boolean isValidIPv6Part(String partStr) {
        // 检查 片段字符串的长度
        if (partStr == null || partStr.length() == 0) return false;
        if (partStr.length() > 4) return false;

        // 检查 片段字符串中的每个字符 是否为 十六进制字符
        for (char currentChar : partStr.toCharArray()) {
            if (!isHexChar(currentChar)) {
                return false;
            }
        }
        return true;
    }

    // 判断一个字符 是不是 十六进制数的字符
    private boolean isHexChar(char passedChar) {
        return (passedChar >= '0' && passedChar <= '9') ||
                (passedChar >= 'a' && passedChar <= 'f') ||
                (passedChar >= 'A' && passedChar <= 'F');
    }
}
