package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06;

public class SearchResult implements Comparable<SearchResult> { // 实现Comparable接口时，添加上泛型限定 - 这样在编译阶段，就能发现传入的参数是不是 SearchResult对象
    int relativeRatio;
    long count;
    int recentOrders;

    public SearchResult(int relativeRatio, long count) {
        this.relativeRatio = relativeRatio;
        this.count = count;
    }

    // 给当前对象，添加比较的逻辑 - 手段：重写compareTo()方法 正数表示更大
    @Override
    public int compareTo(SearchResult o) {
        // 先比较相关度
        if (this.relativeRatio != o.relativeRatio) {
            return this.relativeRatio > o.relativeRatio ? 1 : -1;
        }

        // 相关度相等时, 再比较浏览数
        if (this.count != o.count) {
            return this.count > o.count ? 1 : -1;
        }

        // 完全相等，则：返回0
        return 0;
    }

    // 这是做什么用的？
    public void setRecentOrders(int recentOrders) {
        this.recentOrders = recentOrders;
    }
}
