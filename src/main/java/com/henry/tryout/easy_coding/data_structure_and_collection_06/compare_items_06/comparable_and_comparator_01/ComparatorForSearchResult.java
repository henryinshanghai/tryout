package com.henry.tryout.easy_coding.data_structure_and_collection_06.compare_items_06.comparable_and_comparator_01;

import java.util.Comparator;

// 验证：开发者 能够为 既有的类(可能无法修改其源码) 指定 其比较大小的规则(符合业务)；
// 具体手段：① 自定义比较器类 实现Comparator接口； ② 重写compare()方法，并 在其中 指定比较逻辑
// 用法：在 进行对象之间的比较 时，传入 该自定义的比较器
public class ComparatorForSearchResult implements Comparator<ComparableSearchResult> { // ①

    @Override
    public int compare(ComparableSearchResult o1, ComparableSearchResult o2) { // ②
        // 浏览数 是第一因素
        if (o1.count != o2.count) {
            return o1.count > o2.count ? 1 : -1;
        }

        // 相关度是第二因素 - 使用卫语句来避免过多的if/else嵌套
        if (o1.relativeRatio != o2.relativeRatio) {
            return o1.relativeRatio > o2.relativeRatio ? 1 : -1; // 大则返回正数
        }

        // 最近订单数 是第三因素
        if (o1.recentOrders != o2.recentOrders) {
            return o1.recentOrders > o2.recentOrders ? 1 : -1;
        }

        return 0;
    }
}
