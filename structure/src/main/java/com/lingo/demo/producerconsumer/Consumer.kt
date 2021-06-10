package com.lingo.demo.producerconsumer

interface Consumer<P> {

    fun consume(queue: Queue<P>)
}