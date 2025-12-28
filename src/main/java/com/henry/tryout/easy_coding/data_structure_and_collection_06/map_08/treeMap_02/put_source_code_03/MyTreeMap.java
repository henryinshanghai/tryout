package com.henry.tryout.easy_coding.data_structure_and_collection_06.map_08.treeMap_02.put_source_code_03;

import java.util.*;

// 验证：#1 红黑树中的节点 需要记录 3个链接信息 - 左子节点、右子节点、父节点
// #2 插入节点 的分类讨论 {① 向空树中 插入节点, ② 向非空树中 插入节点}
// #3 向 非空树 插入节点的分类讨论 {① 传入了比较器, ② 未传入比较器}
// #4 插入节点的 高层次步骤：① 先找到 节点的插入位置(BST规则); ② 插入节点后，再通过 重新着色、旋转等手段 恢复平衡
public class MyTreeMap<K, V> { // extends AbstractMap<K, V>
    // implements NavigableMap<K, V>, Cloneable, java.io.Serializable

    // 排序时所会用到的比较器
    private final Comparator<? super K> comparator;

    public MyTreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    // 根节点
    private transient Entry<K, V> root;
    private transient int size = 0;
    private transient int modCount = 0;

    // 常量 - 有字面含义
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    // 内部类 - 用来存储 红黑树中的节点
    static final class Entry<K, V> { // implements Map.Entry<K, V>
        K key;
        V value;
        Entry<K, V> leftSubNode;
        Entry<K, V> rightSubNode;
        Entry<K, V> parentNode;

        // 节点的颜色 - 默认为黑色
        boolean color = BLACK;

        Entry(K key, V value, Entry<K, V> parentNode) {
            this.key = key;
            this.value = value;
            this.parentNode = parentNode;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    // 添加节点 put() 删除节点 deleteEntry()
    public V put(K passedKey, V associatedValue) {
        // 当前节点t - 当前节点为根节点
        Entry<K, V> currentNode = root;

        // Ⅰ 如果 根节点 为null(说明是一个空树)，则：新增的节点 就是 根节点
        if (currentNode == null) {
            // 预检查 Key是不是 能够进行比较
            compare(passedKey, passedKey);

            // 创建节点 - 第三个参数是 parent节点，这里根节点没有parent节点
            root = new Entry<>(passedKey, associatedValue, null);
            size = 1;
            modCount++;
            return null;
        }

        // 准备一个整数，用来 接收 比较结果
        int compareResult;
        // 准备一个parent节点，并初始化为null - 用于创建新节点
        Entry<K, V> parentNodeToAttach;

        // 准备一个比较器变量 - 用来接收从构造方法中传入的比较器
        Comparator<? super K> passedComparator = comparator;

        /* Ⅱ 按照 二叉查找树的约束，找到 新节点的插入位置 */
        if (passedComparator != null) {
            // 循环目标：不断比较 参数传入的key 与 当前节点的key
            do {
                // 把 查找过程结束时的当前节点 作为 新插入节点的父节点
                parentNodeToAttach = currentNode;
                // 比较key的大小
                compareResult = passedComparator.compare(passedKey, currentNode.key);
                if (compareResult < 0) {
                    currentNode = currentNode.leftSubNode; // 游标向左走 手段：把游标指向它的左子节点
                } else if (compareResult > 0) {
                    currentNode = currentNode.rightSubNode;
                } else {
                    // 如果相等，则：使用传入参数覆盖当前节点的value值，并返回更新前的值？
                    return currentNode.setValue(associatedValue);
                }
            } while (currentNode != null); // 如果没有相等的key，则：会一直遍历，直到NIL节点为止
        } else {
            if (passedKey == null) {
                throw new NullPointerException();
            }
            Comparable<? super K> comparablePassedKey = (Comparable<? super K>) passedKey;

            do {
                // 把 查找过程结束时的当前节点 作为 新插入节点的父节点
                parentNodeToAttach = currentNode;
                compareResult = comparablePassedKey.compareTo(currentNode.key);
                if (compareResult < 0) {
                    currentNode = currentNode.leftSubNode;
                } else if (compareResult > 0) {
                    currentNode = currentNode.rightSubNode;
                } else {
                    return currentNode.setValue(associatedValue);
                }
            } while (currentNode != null);
        }

        // Ⅲ 新节点 终于找到了 自己的位置 - 创建 Entry对象，并 把parent作为第三个参数
        Entry<K, V> insertedNewNode = new Entry<>(passedKey, associatedValue, parentNodeToAttach);
        if (compareResult < 0) {
            // 把 新节点 以左子节点 插入
            parentNodeToAttach.leftSubNode = insertedNewNode;
        } else {
            parentNodeToAttach.rightSubNode = insertedNewNode;
        }

        // Ⅳ 对 节点 进行重新着色 与 旋转操作，使红黑树 达到平衡 - 这是 插入节点之后 所执行的操作
        // 如果 代码能够执行到这里，说明：
        // ① 添加节点之前的树 是一棵 非空的树； ② 新节点的Key 与 任何节点 都不相同(否则会 更新节点，而不是 插入节点)
        fixAfterInsertion(insertedNewNode);

        // 添加新节点后，更新相关的属性值
        size++;
        modCount++;

        // 成功添加新节点后，返回null
        return null;
    }


    private void fixAfterInsertion(Entry<K, V> currentNode) {
        // 新节点默认为红色节点
        currentNode.color = RED;

        // 如果 新节点是根节点 或者 新节点的父节点是黑色 时，则：插入红节点 不会破坏 红黑树的约束条件 不需要调整
        // 如果 出现了 连续的红色节点，则：需要 进行旋转 或者 变色操作 - 直到 父节点是黑色，或者 到达根节点
        // 终止调整的三个条件：① 新节点 为 根节点； ② 新节点的父节点 是黑色； ③ 当前节点 为null- 说明 树中的节点 已经调整完毕；
        // 主分类讨论（结构特性） - {Ⅰ 新增节点的父亲 是 其爷爷的左子节点；Ⅱ 新增节点的父亲 是 其爷爷的右子节点}
        // 子分类讨论（颜色特性） - {① 右叔为红色(全部重新着色)；② 右叔为黑色}
        // 右叔为黑色的情况下 - 如果 插入节点 是 父节点的右子节点，则：左旋转 父节点

        while (currentNode != null && currentNode != root && currentNode.parentNode.color == RED) {
            // Ⅰ - 如果 新增节点的父亲 是 爷爷的左子节点（aka 新增节点是长子长孙）
            if (parentOf(currentNode) == leftOf(parentOf(parentOf(currentNode)))) {
                // 获取到 爷爷的右子节点(右叔)
                Entry<K, V> uncleNode = rightOf(parentOf(parentOf(currentNode)));

                // Ⅰ-① 如果 右叔是红色，说明 当前节点的父节点 与 二叔节点 都是红色的，当前情形违反了约束4，
                if (colorOf(uncleNode) == RED) { // 近亲变色
                    /* 则：通过 局部颜色调整 就能够 使子树变成一棵红黑树 */
                    // 把 父节点 染成黑色
                    setColor(parentOf(currentNode), BLACK);
                    // 把 叔叔节点 也染成黑色
                    setColor(uncleNode, BLACK);
                    // 把 爷爷节点 染成红色
                    setColor(parentOf(parentOf(currentNode)), RED);

                    // 现在 爷爷节点 可能和 它的父节点 又形成“双红”，所以 把 当前节点的指针 上移到 爷爷节点，继续循环
                    currentNode = parentOf(parentOf(currentNode));
                } else { // Ⅰ-② 如果 叔叔节点 是黑色节点，说明 不能再只靠颜色反转 来 修复breach(因为颜色翻转后会破坏黑高一致的约束)，则：
                    /* 根据特定情形，按需旋转 */
                    // 情形#1：如果 当前节点 是 其父亲节点的右子节点，说明是 折线型的场景，
                    if (currentNode == rightOf(parentOf(currentNode))) {
                        /* 则：step1.先对 父亲节点 做一次左旋转操作，转换成 直线型的场景 */
                        // 对 父节点 进行 左旋转操作，红色的父亲 会沉入 左侧位置
                        currentNode = parentOf(currentNode);
                        rotateLeft(currentNode);
                    }

                    /* step2. 再对 直线型场景 进行处理；*/
                    // 手段：把 父亲节点 染黑，把 爷爷节点 染红，并对 爷爷节点 进行 右旋操作
                    setColor(parentOf(currentNode), BLACK);
                    setColor(parentOf(parentOf(currentNode)), RED);
                    rotateRight(parentOf(parentOf(currentNode)));

                }
            } else {
                // 如果父亲 是爷爷的右子节点
            }
        }
    }

    private void rotateRight(Entry<K, V> p) {
        if (p != null) {
            Entry<K, V> l = p.leftSubNode;
            p.leftSubNode = l.rightSubNode;
            if (l.rightSubNode != null) l.rightSubNode.parentNode = p;
            l.parentNode = p.parentNode;
            if (p.parentNode == null)
                root = l;
            else if (p.parentNode.rightSubNode == p)
                p.parentNode.rightSubNode = l;
            else p.parentNode.leftSubNode = l;
            l.rightSubNode = p;
            p.parentNode = l;
        }
    }

    private void rotateLeft(Entry<K, V> pivotNode) { // 失去平衡了的子树的根节点
        if (pivotNode != null) {
            // ① 获取 pivotNode的右子节点，将它作为 新的根节点。用于 替换 旋转前的根节点
            Entry<K, V> replacerNode = pivotNode.rightSubNode;

            /* 处理 pivotNode 与 replacerNode的左子树 的关系 👇 */
            // ②-1 将 replacerNode的左子树 设置为 pivotNode的右子树
            pivotNode.rightSubNode = replacerNode.leftSubNode;

            // ②-2 如果 replacerNode的左子树 不为空，则：把 pivotNode 设置为 replacerNode的左子树 的父亲
            if (replacerNode.leftSubNode != null)
                replacerNode.leftSubNode.parentNode = pivotNode;

            /* 处理 parentNode的父节点 与 replacerNode 的关系 👇 */
            // ③-1 把 replacerNode的父亲 设置成为 parentNode的父亲
            replacerNode.parentNode = pivotNode.parentNode;

            // ③-2 把 replacerNode 设置成 pivotNode的父亲的子节点
            if (pivotNode.parentNode == null)
                root = replacerNode;
            else if (pivotNode.parentNode.leftSubNode == pivotNode)
                pivotNode.parentNode.leftSubNode = replacerNode;
            else
                pivotNode.parentNode.rightSubNode = replacerNode;

            /* 处理 pivotNode 与 replacerNode 之间的关系 */
            // 把 pivotNode 设置为 replacerNode的左子树， 把 replacerNode 设置为 pivotNode的父亲
            replacerNode.leftSubNode = pivotNode; // 左旋
            pivotNode.parentNode = replacerNode;
        }
    }

    /* 辅助方法 👇 */
    private static <K, V> void setColor(Entry<K, V> p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private static <K, V> boolean colorOf(Entry<K, V> p) {
        // nil节点的颜色是黑色
        return (p == null ? BLACK : p.color);
    }

    private static <K, V> Entry<K, V> rightOf(Entry<K, V> p) {
        return (p == null ? null : p.rightSubNode);
    }

    // 获取父节点
    private static <K, V> Entry<K, V> parentOf(Entry<K, V> p) {
        // 根节点的父节点 可能为 null
        return (p == null ? null : p.parentNode);
    }

    private static <K, V> Entry<K, V> leftOf(Entry<K, V> p) {
        return (p == null) ? null : p.leftSubNode;
    }

    // 比较 Key-Value中的Key，以便 进行排序
    final int compare(Object k1, Object k2) {
        return comparator == null ? // 如果 使用者 没有传入 指定的comparator
                ((Comparable<? super K>) k1).compareTo((K) k2) : // 则：使用 对象本身的compareTo()方法 进行比较
                comparator.compare((K) k1, (K) k2); // 否则，使用 传入的comparator的compare()方法 进行比较
    }
}
