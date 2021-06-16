package com.lingo.demo.generics.shop;

public interface Shop<T> {
    T buy();

    float refund(T item);
}
