package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06;

import java.util.Comparator;

public class SearchResultComparator_02 implements Comparator<SearchResult> {

    @Override
    public int compare(SearchResult o1, SearchResult o2) {
        // 相关度是第一因素 - 使用卫语句来避免过多的if/else嵌套
        if (o1.relativeRatio != o2.relativeRatio) {
            return o1.relativeRatio > o2.relativeRatio ? 1 : -1;
        }

        // 最近订单数是第二因素
        if (o1.recentOrders != o2.recentOrders) {
            return o1.recentOrders > o2.recentOrders ? 1 : -1;
        }

        // 浏览数是第三因素
        if (o1.count != o2.count) {
            return o1.count > o2.count ? 1 : -1;
        }

        return 0;
    }
}
