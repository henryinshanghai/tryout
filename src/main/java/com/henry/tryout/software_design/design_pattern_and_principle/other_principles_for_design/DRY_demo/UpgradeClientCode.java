package com.henry.tryout.software_design.design_pattern_and_principle.other_principles_for_design.DRY_demo;

public class UpgradeClientCode {
    public void printBalance(final Account account) {
        // NEXT 对数值格式化的代码（%10.2f）反复出现 - 可以消除它
        System.out.printf("Debits: %10.2f\n", account.getDebits());
        System.out.printf("Credits: %10.2f\n", account.getCredits());

        System.out.printf("----------\n");

        System.out.printf("Fees: %10.2s\n", formatValue(account.getBalance()));
        System.out.printf("Balance: %10.2s\n", formatValue(account.getBalance()));

    }

    // 引入了一个方法，以此来消除+/-语句之间的重复
    String formatValue(final Double value) {
        String result = String.format("%10.2f", Math.abs(value));

        if (value < 0) {
            return result + "-";
        } else {
            return result + "+";
        }
    }
}
