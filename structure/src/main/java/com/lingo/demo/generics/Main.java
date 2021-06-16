package com.lingo.demo.generics;

import com.lingo.demo.generics.fruit.Apple;
import com.lingo.demo.generics.fruit.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<? super Apple> list = new ArrayList<Fruit>();
        list.set(0, new Apple());
        Object object = list.get(0);
        addToList(new ArrayList<Object>(), new Apple());
    }

    private static void addToList(List<? super Fruit> list, Fruit fruit) {
        list.add(fruit);
    }
}

