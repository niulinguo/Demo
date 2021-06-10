package com.lingo.demo.producerconsumer

interface Consumer<P> {
    fun consume(queue: Queue<P>): P
}

interface ConsumerWithTime<P> {
    fun consumeWithTime(queue: Queue<P>, mills: Long): P?
}