package com.lingo.demo.generics.shop;

public interface RepairableShop<T> extends Shop<T> {
    void repair(T item);
}
