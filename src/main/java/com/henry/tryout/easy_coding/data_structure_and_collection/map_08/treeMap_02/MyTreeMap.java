package com.henry.tryout.easy_coding.data_structure_and_collection.map_08.treeMap_02;

import java.util.*;

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

    // 内部类 - 用来存储红黑树中的节点
    static final class Entry<K, V> { // implements Map.Entry<K, V>
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;

        // 节点的颜色 - 默认为黑色
        boolean color = BLACK;

        Entry(K key, V value, Entry<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
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
    public V put(K key, V value) {
        // 当前节点t - 当前节点为根节点
        Entry<K, V> t = root;

        // Ⅰ 如果根节点为null(说明是一个空树)，则：新增的节点就是根节点
        if (t == null) {
            // 预检查 Key是不是能够进行比较
            compare(key, key);

            // 创建节点 - 第三个参数是 parent节点，这里根节点没有parent节点
            root = new Entry<>(key, value, null);
            size = 1;
            modCount++;
            return null;
        }

        // 准备一个整数，用来接收比较结果
        int cmp;
        // 准备一个parent节点，并初始化为null - 用于创建新节点
        Entry<K, V> parent;

        // 准备一个比较器变量 - 用来接收从构造方法中传入的比较器
        Comparator<? super K> cpr = comparator;

        /* Ⅱ 按照二叉查找树的约束，找到新节点的插入位置 */
        if (cpr != null) {
            // 循环目标：不断比较 参数传入的key 与 当前节点的key
            do {
                // 把 currentCursor指向 parent - 从根节点开始遍历比较
                parent = t;
                // 比较key的大小
                cmp = cpr.compare(key, t.key);
                if (cmp < 0) {
                    t = t.left; // 游标向左走 手段：把游标指向它的左子节点
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    // 如果相等，则：使用传入参数覆盖当前节点的value值，并返回更新前的值？
                    return t.setValue(value);
                }
            } while (t != null); // 如果没有相等的key，则：会一直遍历，直到NIL节点为止
        } else {
            if (key == null) {
                throw new NullPointerException();
            }
            Comparable<? super K> k = (Comparable<? super K>) key;

            do {
                parent = t;
                cmp = k.compareTo(t.key);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    return t.setValue(value);
                }
            } while (t != null);
        }

        // Ⅲ 新节点终于找到自己的位置 - 创建 Entry对象，并把parent作为第三个参数
        Entry<K, V> e = new Entry<>(key, value, parent);
        if (cmp < 0) {
            parent.left = e; // 如果比较结果小于0，则：当前节点成为 parent的左子节点
        } else {
            parent.right = e;
        }

        // Ⅳ 对节点进行重新着色与旋转操作，使红黑树达到平衡 - 这是插入节点之后所执行的操作
        // 如果 代码能够执行到这里，说明：1 添加节点之前的树是一棵非空的树； 2 新节点的Key与任何节点都不相同(否则会更新节点，而不是插入节点)
        fixAfterInsertion(e);

        // 添加新节点后，更新相关的属性值
        size++;
        modCount++;

        // 成功添加新节点后，返回null
        return null;
    }


    private void fixAfterInsertion(Entry<K,V> x) {
        // 新节点默认为红色节点
        x.color = RED;

        // 如果新节点是根节点 或者 新节点的父节点是黑色时，则：插入红节点不会破坏红黑树的约束条件 不需要调整
        // 如果出现了连续的红色节点，则：需要进行旋转或者变色操作 - 直到父节点是黑色，或者到达根节点
        // 终止调整的三个条件：#1 新节点为根节点； #2 新节点的父节点是黑色； #3 当前节点为null- 说明树中的节点已经调整完毕；
        while (x != null && x != root && x.parent.color == RED) {
            // Ⅰ - 如果新增节点的父亲 是爷爷的左子节点
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                // 获取到爷爷的右子节点(右叔)
                Entry<K, V> y = rightOf(parentOf(parentOf(x)));

                // Ⅰ-① 如果右叔是红色，则：通过局部颜色调整就能够 使子树变成一棵红黑树
                if (colorOf(y) == RED) { // 近亲变色
                    // 把父亲置为黑色
                    setColor(parentOf(x), BLACK);
                    // 把右叔置为黑色
                    setColor(y, BLACK);
                    // 把爷爷设置为红色
                    setColor(parentOf(parentOf(x)), RED);

                    // 爷爷成为新的节点，进入到下一轮循环 - 用爷爷节点更新当前节点x（这就是为什么x可能为null）
                    x = parentOf(parentOf(x));
                } else { // Ⅰ-② 如果右叔是黑色节点
                    // 如果x是父亲的右节点，则：先对父亲做一次左旋转操作，转换成x是父亲的左子节点的情形
                    if (x == rightOf(parentOf(x))) {
                        // 对父节点进行左旋转操作，红色的父亲会沉入左侧位置
                        // 把父亲赋值给x ？？？
                        x = parentOf(x);
                        rotateLeft(x);
                    }

                    // 重新着色，并对x的爷爷进行右旋操作
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));

                }
            } else {
                // 如果父亲 是爷爷的右子节点
            }
        }
    }

    private void rotateRight(Entry<K,V> p) {
        if (p != null) {
            Entry<K,V> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }

    private void rotateLeft(Entry<K,V> p) { // 失去平衡了的子树的根节点
        if (p != null) {
            // ① 获取p的右子节点
            Entry<K,V> r = p.right;
            /* 处理p与r左子树的关系 👇 */
            // ②-1 将r的左子树设置为p的右子树
            p.right = r.left;

            // ②-2 如果r的左子树不为空，则：把p设置为r左子树的父亲
            if (r.left != null)
                r.left.parent = p;

            /* 处理p的父节点 与 r的关系 👇 */
            // ③-1 把p的父亲设置成为r的父亲
            r.parent = p.parent;

            // ③-2 把r设置成 p的父亲的子节点
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;

            /* 处理p节点 与 r节点之间的关系 */
            // 把p设置为r的左子树， 把r设置为p的父亲
            r.left = p; // 左旋
            p.parent = r;
        }
    }

    /* 辅助方法 👇 */
    private static <K,V> void setColor(Entry<K,V> p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private static <K,V> boolean colorOf(Entry<K,V> p) {
        // nil节点的颜色是黑色
        return (p == null ? BLACK : p.color);
    }

    private static <K,V> Entry<K,V> rightOf(Entry<K,V> p) {
        return (p == null ? null: p.right);
    }

    // 获取父节点
    private static <K,V> Entry<K,V> parentOf(Entry<K,V> p) {
        // 根节点的父节点 可能为 null
        return (p == null ? null: p.parent);
    }

    private static <K,V> Entry<K,V> leftOf(Entry<K,V> p) {
        return (p == null) ? null: p.left;
    }

    // 比较 Key-Value中的Key，以便进行排序
    final int compare(Object k1, Object k2) {
        // 有 comparator的话，优先使用 comparator,否则的话，使用对象自己实现的 compareTo()方法
        return comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2)
                : comparator.compare((K)k1, (K)k2);
    }
}
