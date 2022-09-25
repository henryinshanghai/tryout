package com.henry.tryout.easy_coding.data_structure_and_collection_06.compare_items_06.comparable_and_comparator_01;

import java.util.Arrays;

// 在排序时使用 比较器的经典案例 - Arrays.sort(collection, comparator)
public class SortViaCompare {
    public static void main(String[] args) {
        SearchResult[] searchResultArr = new SearchResult[4];
        searchResultArr[0] = new SearchResult(5, 100);
        searchResultArr[1] = new SearchResult(10, 50);
        searchResultArr[2] = new SearchResult(2, 1000);
        searchResultArr[3] = new SearchResult(5, 150);

        System.out.println("按照 SearchResult中定义的比较规则(相关度)进行排序 👇");
        // #1 按照 自定义类型中定义的比较规则，对集合元素排序
        Arrays.sort(searchResultArr);

        for (SearchResult searchResult : searchResultArr) {
            System.out.println(searchResult + "-");
        }

        System.out.println("按照比较器的比较规则(浏览量)进行排序 👇");

        // #2 在比较时，传入自定义的比较器。从而按照比较器中的规则 对集合元素进行排序
        Arrays.sort(searchResultArr, new SearchResultComparator()); // 这里也可以使用匿名类 - 直接new Comparator接口类型

        // 检查数组中的元素 是否按照需要进行排序了 - 需要 自定义类型重写toString()方法
        for (SearchResult searchResult : searchResultArr) {
            System.out.println(searchResult + "~");
        }
    }
}
/*
sort(T[] a, Comparator<? super T> c){...}

<? super T> 表示：集合中的类型约束为 - T与T的父类(类型的上限是 Object)；
用法：在方法的形参中， 用来限定实际传入的参数。
 */
