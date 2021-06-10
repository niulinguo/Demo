package com.lingo.demo.producerconsumer

interface Queue<P> {

    fun addProduct(p: P)

    fun removeProduct(): P

}