package com.henry.tryout.java_8_features.do_collection_with_stream_03;

import com.henry.tryout.java_8_features.stream_02.Trader;
import com.henry.tryout.java_8_features.stream_02.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class groupTransactionByCurrency {
    public static void main(String[] args) {

        List<Transaction> transactions = getTransactions();

        // 根据交易的货币来对交易进行分组
        /*
            在 流上调用 collect(<operation>)方法会 对流中的元素触发一个 归约操作 - 这个归约操作由 Collector来具体指定
            特征：
                Collector类提供了很多静态方法(toList等) 用来 指定归约操作
         */
        Map<Trader, List<Transaction>> transactionsGroupByTrader =
                transactions.stream()
                .collect(groupingBy(Transaction::getTrader));

        for (Trader trader : transactionsGroupByTrader.keySet()) {
            System.out.println(trader + " -> " +  transactionsGroupByTrader.get(trader).size());
        }

    }

    private static List<Transaction> getTransactions() {
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
