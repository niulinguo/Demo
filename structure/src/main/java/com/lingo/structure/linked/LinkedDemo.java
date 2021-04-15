package com.lingo.structure.linked;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedDemo {
    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }

    private static void demo1() {
        final Map<Integer, String> map = new HashMap<>();
        printMap(map);
    }

    private static void demo2() {
        final Map<Integer, String> map = new LinkedHashMap<>();
        printMap(map);
    }

    /**
     * LinkedHashMap 是通过双向链表和散列表这两种数据结构组合实现的。
     * LinkedHashMap 中的 Linked 实际上是指的是双向链表，并非指用链表发解决散列冲突
     */
    private static void demo3() {
        // 跟 LruCache 的原理一样
        final Map<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);
        printMap(map);
    }

    private static void printMap(Map<Integer, String> map) {
        map.put(1, "Lingo");
        map.put(5, "Garden");
        map.put(2, "GuoGuo");
        map.put(4, "Jack");
        map.get(2);

        System.out.println(map.getClass().getSimpleName());
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();
    }
}
