package com.henry.tryout.software_design.design_pattern_and_principle.other_principles_for_design.DRY_demo;

public class OriginalClientCode {

    public void printBalance(final Account account) {
        System.out.printf("Debits: %10.2f\n", account.getDebits());
        System.out.printf("Credits: %10.2f\n", account.getCredits());

        if (account.getFees() < 0) {
            System.out.printf("Fees: %10.2f-\n", -account.getFees());
        } else {
            System.out.printf("Fees: %10.2f\n", account.getBalance()); // 12行与10行显然是相互copy，然后改一改的
        }

        System.out.printf("----------\n");

        if (account.getBalance() < 0) {
            System.out.printf("Balance: %10.2f-\n", account.getBalance());
        } else {
            System.out.printf("Balance: %10.2f\n", account.getBalance());
        }
    }
}


