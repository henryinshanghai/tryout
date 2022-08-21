package com.henry.tryout.easy_coding.data_structure_and_collection.collection_framework_02.arraylist_01;

import java.util.Arrays;

/*
    默认容量 = 5
    扩容的规则 = 旧容量+3
    警戒容量 = 11
    最大容量 = 15
 */
public class MyArrayListDrill<E> {
    // 成员变量
    private Object[] elementData;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private static final int DEFAULT_CAPACITY = 5;
    private static final int MAX_ARRAY_SIZE = 10;
    public int size;

    // 构造方法
    public MyArrayListDrill() {
        elementData = EMPTY_ELEMENT_DATA;
    }

    // 核心方法
    public boolean add(E e) {
        // 确保 数组有足够的容量 来容纳新元素
        ensureEnoughSpaceForNewItem(size + 1);
        // 添加元素
        elementData[size++] = e;
        System.out.println("插入元素成功，插入的元素为： " + e);
        return true;
    }

    private void ensureEnoughSpaceForNewItem(int needCapacity) {
        // 手段：判断是不是需要扩容， 如果需要 就进行扩容
        int calculatedNeedCapacity = calculateNeedCapacity(needCapacity);
        growIfNeed(calculatedNeedCapacity);
    }

    private void growIfNeed(int needCapacity) {
        if (needCapacity - elementData.length > 0) {
            System.out.println("elementData数组容量不足，开始扩容...");
            grow(needCapacity);
        }
    }

    private void grow(int needCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + 3; // 扩容规则：每次+3
        if (needCapacity > newCapacity) {
            newCapacity = needCapacity;
        }

        // 根据needCapacity 来 调整 newCapacity的值
        if (newCapacity > MAX_ARRAY_SIZE) {
            System.out.println("预期的新容量已经超过警戒容量，先扩容到警戒容量(需要时扩容到最大容量) 当前所需的数组容量为： " + needCapacity);
            newCapacity = adjustCapacityBasedOn(needCapacity);
        }

        // 把旧的数组 拷贝到 新数组中
        elementData = Arrays.copyOf(elementData, newCapacity);
        System.out.println("扩容成功， 扩容后elementData的容量为： " + newCapacity);
    }

    private int adjustCapacityBasedOn(int needCapacity) {
        if (needCapacity > 15) {
            System.out.println("已经达到最大容量，扩容失败 抛出异常");
            throw new OutOfMemoryError();
        }

        return needCapacity > MAX_ARRAY_SIZE ? 15 : MAX_ARRAY_SIZE;
    }

    private int calculateNeedCapacity(int needCapacity) {
        if (elementData == EMPTY_ELEMENT_DATA) {
            needCapacity = Math.max(needCapacity, DEFAULT_CAPACITY);
            System.out.println("第一次向数组中添加元素，数组的默认容量为：" + needCapacity);
        }

        System.out.println("当前所需要的数组容量为：" + needCapacity);
        return needCapacity;
    }
}
