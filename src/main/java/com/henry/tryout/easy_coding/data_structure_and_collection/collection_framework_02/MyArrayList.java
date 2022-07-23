package com.henry.tryout.easy_coding.data_structure_and_collection.collection_framework_02;


import java.util.Arrays;

public class MyArrayList<E> { // extends AbstractList<E>  implements List<E>, RandomAccess, Cloneable, java.io.Serializable

    transient Object[] elementData;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public MyArrayList() {
        elementData = EMPTY_ELEMENT_DATA;
    }

    public int size() {
        return size;
    }

    public boolean add(E e) {
        // #1 先确保elementData中由足够的空间 来容纳新元素
        ensureEnoughSpaceForNewItem(size + 1);

        System.out.println("添加的元素为： " + e);
        // #2 确保足够的容量后，添加元素
        elementData[size++] = e;

        // 返回boolean值
        return true;
    }

    private void ensureEnoughSpaceForNewItem(int needCapacity) {
        // 这里计算 needCapacity 需要把 elementData作为参数传入进入
        growIfNeeded(calculateNeedCapacity(elementData, needCapacity));
    }

    private void growIfNeeded(int needCapacity) {
        if (elementData.length < needCapacity) {
            System.out.println("当前数组容量为： " + elementData.length + ", elementData数组空间不足，开始扩容😄");
            grow(needCapacity);
        }
    }

    private void grow(int needCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + 2;

        if (newCapacity < needCapacity) {
            newCapacity = needCapacity;
        }

        if (newCapacity > 4) {
            newCapacity = adjustNewCapacity(needCapacity);
        }

        elementData = Arrays.copyOf(elementData, newCapacity);
        System.out.println("扩容结束, 新数组的容量为： " + newCapacity + " 😄");
    }

    private int adjustNewCapacity(int needCapacity) {
        if (needCapacity > 7) { // elementData数组的最大允许容量为 7
            throw new OutOfMemoryError();
        }
        return needCapacity > 4 ? 7 : 4; // elementData数组的警戒容量为4 - aka 一旦所需容量超过一定阈值，就会使用4作为容量
    }

    private int calculateNeedCapacity(Object[] elementData, int needCapacity) {
        // 这里要怎么判断才能相等？- 在构造方法中，完成对 elementData的初始化
        if (elementData == EMPTY_ELEMENT_DATA) {
            System.out.println("首次对列表进行扩容，扩容后数组的容量为 2");
            return 2; // Math.max(DEFAULT_CAPACITY, needCapacity)
        }
        System.out.println("为容纳新元素所需要的容量为： " + needCapacity);
        return needCapacity;
    }
}

