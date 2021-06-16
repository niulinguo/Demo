package com.lingo.demo.generics.shop;

import com.lingo.demo.generics.sim.Sim;

public interface SimShop<T, C extends Sim> extends Shop<T> {
    C getSim(String name, String id);
}
