package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.comparable_and_comparator_01;

import java.util.Arrays;
import java.util.Comparator;

// 在排序时使用 比较器的经典案例 - Arrays.sort(collection, comparator)
public class ComparatorForSort_02 {
    public static void main(String[] args) {
        SearchResult[] searchResultArr = new SearchResult[3];
        searchResultArr[0] = new SearchResult(5, 100);
        searchResultArr[1] = new SearchResult(10, 50);
        searchResultArr[2] = new SearchResult(2, 1000);

        // new Comparator接口类型 - 以此提供Comparator参数
        Arrays.sort(searchResultArr, new Comparator<SearchResult>() {
            @Override
            public int compare(SearchResult o1, SearchResult o2) {
                if (o1.relativeRatio != o2.relativeRatio) {
                    return o1.relativeRatio > o2.relativeRatio ? 1 : -1; // 升序 - 大则返回正数
                }

                if (o1.count != o2.count) {
                    return o1.count > o2.count ? 1 : -1;
                }

                return 0;
            }
        });

        // 检查数组中的元素 是否按照需要进行排序了 - 需要 自定义类型重写toString()方法
        for (SearchResult searchResult : searchResultArr) {
            System.out.println(searchResult);
        }
    }
}
/*
sort(T[] a, Comparator<? super T> c){...}

<? super T> 表示：集合中的类型约束为 - T与T的父类(类型的上限是 Object)；
用法：在方法的形参中， 用来限定实际传入的参数。
 */
