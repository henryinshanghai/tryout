package com.henry.tryout.software_design.design_pattern_and_principle.other_principles_for_design.DRY_demo;

public class UpgradeClientCode03 {
    void printBalance(final Account account) {
        reportLine("Debits", account.getDebits());
    }

    // 引入了一个 reportLine，用来组装内容
    private void reportLine(final String label,final double value) {
        printLine(label + ":", formatValue(value));
    }

    // 引入了一个 printLine，用于格式化打印结果
    void printLine(final String label, final String value) {
        System.out.printf("%-9s%s\n", label, value);
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
