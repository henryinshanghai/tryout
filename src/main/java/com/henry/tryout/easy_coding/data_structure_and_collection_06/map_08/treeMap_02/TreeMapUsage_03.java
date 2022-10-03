package com.henry.tryout.easy_coding.data_structure_and_collection_06.map_08.treeMap_02;

import java.util.TreeMap;

// 验证：动态构建红黑树时的各种case & 针对这些case的解决方案
// case分类讨论 - {父叔都是红色节点(重新着色), 父节点红色 + 叔叔节点黑色 {大儿子(右旋), 小儿子(左旋 )}}
// 重新着色：对谁进行重新着色?   旋转：以谁为中心进行旋转?
// case不是非常具体, 处理手段也不是固定的...
// *4 一个路径上不能出现 "相邻的两个红色节点"；
// *5 在任何递归子树上，根节点到叶子节点的所有路径上，都要包含 "相同数量的黑色节点"。
public class TreeMapUsage_03 {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        // 构建 红黑树
        // 插入55节点 - 作为根节点，节点是黑色的
        treeMap.put(55, "fifty-five");
        // 插入56节点 - 节点插入时为红色，父节点为黑色 不需要做调整
        treeMap.put(56, "fifty-six");
        // 插入57节点 - 节点插入时为红色 父节点为红色，出现了两个连续的红色节点 #case???
        // - 处理手段： 需要重新着色 + 旋转（因为违反了约束5 根节点到叶子节点的黑色节点数量不相同）
        treeMap.put(57, "fifty-seven");

        // 插入58节点 - 父亲是57（红色节点），是爷爷节点56的右子节点，左叔55是红色节点 #case01
        // - 处理手段：涂黑父亲与左叔，然后把爷爷节点置为红色。最后再把根节点(爷爷节点)置为黑色即可
        treeMap.put(58, "fifty-eight");

        // 插入83节点 - 父节点58（红色节点），是爷爷节点57的右子节点; 左叔不存在（认为是黑色的NIL节点）; 83是58的右子节点(小儿子) #case03
        // - 处理手段：涂黑父亲，把爷爷设置为红色。对失衡的爷爷节点(breach 约束5 - 根节点到叶子节点的所有路径上都包含相同数量的黑节点)做左旋操作
        treeMap.put(83, "eighty-three");

        // 删除57节点 - 红色的叶子节点，直接删除就行
        treeMap.remove(57);

        // 插入59节点 - 父亲83是爷爷的右子节点; 左叔是黑色节点; 自己是父亲的左子节点(大儿子) #case02
        // - 处理手段：以83为中心进行右旋操作; 涂黑自己，涂红58; 最后再用58进行左旋
        treeMap.put(59, "fifty-nine");
    }
}
/*
插入节点，导致树需要做出调整的3种情况：
1 如果 父亲节点是红色，叔叔是红色的，则：需要重新着色；
2 如果 父节点是红色的，叔叔节点是黑色的，而且新节点是父亲的左节点，则：需要进行右旋；
3 如果 父亲节点是红色的，叔叔节点是黑色的，而且新节点是父亲的右节点，则：需要进行左旋。

为了恢复平衡，可能需要多次的操作 - 但至多不超过3次（比如节点59）
红黑树的任何不平衡都能再3次旋转中调整完成 - 向上回溯的步长是2 - 对于频繁插入与删除的场景，优势很明显(相比于AVL树)

TreeMap本身是线程不安全的。
 */