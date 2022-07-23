package com.henry.tryout.easy_coding.data_structure_and_collection.array_and_collection_04;

import com.henry.tryout.easy_coding.data_structure_and_collection.collection_framework_02.ArrayList;

import java.util.Arrays;
import java.util.List;

public class ListToArray_03 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        // 列表对象 转换成为 数组对象 - 结果：泛型信息丢失，只会得到 Object类型的数组
        Object[] array1 = list.toArray();

        // 使用一个数组 来 接受列表转化后的数组,且数组容量不足 - 结果：得到了两个null值
        String[] array2 = new String[2];
        list.toArray(array2);
        // 打印 array2中的元素
        System.out.println(Arrays.asList(array2)); // [null, null]

        // 使用一个数组 来 接受列表转化后的数组，且数组容量充足 - 结果：转化成功
        String[] array3 = new String[3];
        list.toArray(array3);
        System.out.println(Arrays.asList(array3)); // [one, two, three]
    }
}

/*
    启示：
        1 不要使用 toArray()把列表转化成为 数组；
        2 为什么 array2会是 [null, null]?
            public <T> T[] toArray(T[] a) {
                // 如果 给出的数组长度 小于 列表size的话，则：
                if (a.length < size)
                    // 会直接return Make a new array of a's runtime type, but my contents:
                    return (T[]) Arrays.copyOf(elementData, size, a.getClass());
                // 2 如果容量足够的话，则：直接复制
                System.arraycopy(elementData, 0, a, 0, size);

                if (a.length > size)
                    a[size] = null;

                // 3 只有在 容量足够的情况下，才会返回传入的参数
                return a;
            }
        3 transient Object[] elementData;
            表示这个字段 在对象序列化的时候会被忽略掉。
            #1 为什么能忽略 真正用来存储item的数组字段呢？
            因为集合一般是这样传输的：
                系统调用 writeObject()完成集合的序列化；
                客户端再使用 readObject()实现 集合的反序列化，此时 会把集合中的item都存储到新对象的 elementData中去。
            所以，elementData这个字段不再需要通过二进制流传输了。
            #2 为什么要忽略 这个真正用于存储item的数组字段呢？
                答：因为 elementData数组容量 一般会大于 真实存储的item数量。所以在传输时，只需要发送 真正有实际值的数组元素就行了

 */
