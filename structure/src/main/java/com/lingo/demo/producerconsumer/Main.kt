package com.lingo.demo.producerconsumer

import java.util.concurrent.atomic.AtomicInteger

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val queue = MyQueue(10)
        val productCount = 3
        val consumerCount = 3
        for (i in 0 until productCount) {
            Thread(MyProducer(queue), "product${i}").start()
        }
        for (i in 0 until consumerCount) {
            Thread(MyConsumer(queue), "consumer${i}").start()
        }
    }
}

class Product(val name: String)

class MyQueue(private val size: Int) : Queue<Product> {
    private val deque: ArrayDeque<Product> = ArrayDeque(size)
    private val lock = Object()

    override fun addProduct(p: Product) {
        synchronized(lock) {
            while (deque.size > size) {
                println("生产过剩，${Thread.currentThread().name}等待")
                lock.wait()
            }
            deque.addLast(p)
            println("${Thread.currentThread().name}生产了${p.name}")
            lock.notify()
        }
    }

    override fun removeProduct(): Product {
        synchronized(lock) {
            while (deque.size == 0) {
                println("产品不足，${Thread.currentThread().name}等待")
                lock.wait()
            }
            val p: Product = deque.removeFirst()
            println("${Thread.currentThread().name}消费了${p.name}")
            lock.notify()
            return p
        }
    }
}

class MyProducer(private val queue: Queue<Product>) : Producer<Product>, Runnable {
    companion object {
        private val atomicInt = AtomicInteger(0)
    }

    override fun product(queue: Queue<Product>) {
        queue.addProduct(Product("p_${atomicInt.getAndIncrement()}"))
    }

    override fun run() {
        while (true) {
            product(queue)
            Thread.sleep(10)
        }
    }
}

class MyConsumer(private val queue: Queue<Product>) : Consumer<Product>, Runnable {
    override fun consume(queue: Queue<Product>) {
        queue.removeProduct()
    }

    override fun run() {
        while (true) {
            consume(queue)
            Thread.sleep(10)
        }
    }
}