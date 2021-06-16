package com.lingo.demo.generics.fruit;

import org.jetbrains.annotations.NotNull;

public class Apple implements Fruit {
    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public int compareTo(@NotNull Fruit fruit) {
        return Integer.compare(getWeight(), fruit.getWeight());
    }
}
