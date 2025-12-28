package com.henry.tryout.easy_coding.data_structure_and_collection_06.compare_items_06.comparable_and_comparator_01;

// 验证：开发者能够定义一个 可以比较大小(Comparable)的类型；
// 手段：自定义类型时, ① 实现 Comparable接口；② 重写compareTo()方法，在方法中 指定对象比较大小的规则；
public class ComparableSearchResult implements Comparable<ComparableSearchResult> { // 实现Comparable接口时，添加上泛型限定 - 这样在编译阶段，就能发现传入的参数是不是 SearchResult对象
    int relativeRatio;
    long count;
    int recentOrders;

    public ComparableSearchResult(int relativeRatio, long count) {
        this.relativeRatio = relativeRatio;
        this.count = count;
    }

    // #2 重写compareTo()方法 - 作用：指定比较的逻辑
    @Override
    public int compareTo(ComparableSearchResult o) { // 参数：同一类型的其他对象o
        // 先比较相关度 - 如果不相等，则：根据具体大小，返回 1/-1
        if (this.relativeRatio != o.relativeRatio) {
            return this.relativeRatio > o.relativeRatio ? 1 : -1;
        }

        // 相关度相等时, 再比较浏览数 - 如果不相等，则：根据具体大小，返回 1/-1
        if (this.count != o.count) {
            return this.count > o.count ? 1 : -1;
        }

        // 如果运行到此处，说明relativeRatio 与 count都相等，则：返回0 - 表示对象的属性一模一样
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
