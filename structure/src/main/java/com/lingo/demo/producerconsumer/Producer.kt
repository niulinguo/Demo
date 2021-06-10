package com.lingo.demo.producerconsumer

interface Producer<P> {

    fun product(queue: Queue<P>)
}