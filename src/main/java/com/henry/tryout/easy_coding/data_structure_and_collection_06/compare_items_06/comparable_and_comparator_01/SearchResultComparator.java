package com.henry.tryout.easy_coding.data_structure_and_collection_06.compare_items_06.comparable_and_comparator_01;

import java.util.Comparator;

// 定义符合业务规则的比较器 - 在进行比较时，就能够按照需要传入比较器
public class SearchResultComparator implements Comparator<SearchResult> {

    @Override
    public int compare(SearchResult o1, SearchResult o2) {
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
