package com.henry.tryout.java_8_features.stream_02.practice_06;

import com.henry.tryout.java_8_features.stream_02.Trader;
import com.henry.tryout.java_8_features.stream_02.Transaction;

import java.util.*;
import java.util.stream.Collectors;

// 验证：利用Stream - 可以以声明式的方式 来 处理集合中的元素
public class Purpose_and_approach_01 {
    public static void main(String[] args) {
        List<Transaction> transactions = prepareData();

        // 找出2011年的所有交易，并按照交易额从低到高排序 - sorted()方法
        printTransactionIn2011(transactions);

        // 找出所有交易员工作的城市集合 - collect(Collectors.toSet())方法
        printCities(transactions);

        // 找出所有来自于剑桥的交易员
        printTraderFromCambridge(transactions);

        // 找到所有的交易员，并把名字字符串连到一起 - reduce("", (name1, name2) -> name1 + name2)
        printTraderNames(transactions);

        // 有没有交易员在米兰工作
        printAnyTraderWorkInMilan(transactions);

        // 获取在剑桥的交易员的所有交易的金额 - .forEach(System.out::println);
        printTransactionValueFromTraderInCam(transactions);

        // 获取到最高交易额 - highestValue.orElse(null)
        printTheMaxTransactionValue(transactions);

        // 获取到交易额最低的交易 - item与item之间的操作
        printTheMinTransaction(transactions);
    }

    private static void printTheMinTransaction(List<Transaction> transactions) {
        Optional<Transaction> smallestTransaction = transactions.stream()
                .reduce(((transaction1, transaction2) -> // item与item之间的操作
                        transaction1.getValue() < transaction2.getValue() ? transaction1 : transaction2));

        System.out.println("最小的交易是： " + smallestTransaction.orElse(null));
    }

    private static void printTheMaxTransactionValue(List<Transaction> transactions) {
        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        System.out.println("最大交易的金额为： " + highestValue.orElse(null));
    }

    private static void printTransactionValueFromTraderInCam(List<Transaction> transactions) {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    private static void printAnyTraderWorkInMilan(List<Transaction> transactions) {
        boolean isTraderInMilanExist = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader()
                        .getCity().equals("Milan"));

        if (isTraderInMilanExist) {
            System.out.println("存在生活在米兰的交易员");
        }
    }

    private static void printTraderNames(List<Transaction> transactions) {
        String traderNames = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .reduce("", (name1, name2) -> name1 + name2); // 这里需要一个原始的空字符串

        System.out.println("拼接得到的姓名字符串： " + traderNames);
    }

    private static void printTraderFromCambridge(List<Transaction> transactions) {
        List<Trader> traderFromCambridge = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        traderFromCambridge.forEach(System.out::println);

    }

    private static void printCities(List<Transaction> transactions) {
        Set<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
//                .distinct()
                .collect(Collectors.toSet());// toList()

        cities.forEach(System.out::println);
    }

    private static void printTransactionIn2011(List<Transaction> transactions) {
        List<Transaction> transactionIn2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        transactionIn2011.forEach(System.out::println);
    }

    private static List<Transaction> prepareData() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brain = new Trader("Brian", "Cambridge");

         return Arrays.asList(
                new Transaction(brain, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
}
