package com.henry.tryout.easy_coding.data_structure_and_collection_06.compare_items_06.comparable_and_comparator_01;

// 对象之间的大小比较 - 手段1：使自定义的类 可被比较(Comparable)
// 具体做法：自定义类型时, #1 实现 Comparable接口
public class SearchResult implements Comparable<SearchResult> { // 实现Comparable接口时，添加上泛型限定 - 这样在编译阶段，就能发现传入的参数是不是 SearchResult对象
    int relativeRatio;
    long count;
    int recentOrders;

    public SearchResult(int relativeRatio, long count) {
        this.relativeRatio = relativeRatio;
        this.count = count;
    }

    // #2 重写compareTo()方法 - 作用：指定比较的逻辑
    @Override
    public int compareTo(SearchResult o) { // 参数：同一类型的其他对象o
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

    @Override
    public String toString() {
        return "SearchResult{" +
                "relativeRatio=" + relativeRatio +
                ", count=" + count +
                ", recentOrders=" + recentOrders +
                '}';
    }
}
