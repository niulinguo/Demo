package com.lingo.demo.producerconsumer

interface Queue<P> {

    fun product(p: P)

    fun consume(): P

    fun consumeWithTime(mills: Long): P?
}