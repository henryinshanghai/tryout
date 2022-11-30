package com.henry.tryout.software_design.design_pattern_and_principle.other_principles_for_design.DRY_demo;

public class UpgradeClientCode02 {

    public void printBalance(final Account account) {
        // 对所有的数值调用formatValue()方法 - 这样格式化数值的代码就能够统一
        // NEXT 这里打印格式也是重复出现的 - 不好维护 比如要添加一个空格啥的
        System.out.printf("Debits: %10.2s\n", formatValue(account.getDebits()));
        System.out.printf("Credits: %10.2s\n", formatValue(account.getCredits()));

        System.out.printf("----------\n");

        System.out.printf("Fees: %10.2s\n", formatValue(account.getBalance()));
        System.out.printf("Balance: %10.2s\n", formatValue(account.getBalance()));

    }

    String formatValue(final Double value) {
        String result = String.format("%10.2f", Math.abs(value));

        if (value < 0) {
            return result + "-";
        } else {
            return result + "+";
        }
    }

}
