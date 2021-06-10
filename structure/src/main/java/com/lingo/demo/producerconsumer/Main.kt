package com.lingo.demo.producerconsumer

import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val queue = MyQueue(10)
        val productCount = 3
        val consumerCount = 3
        val flag = CountDownLatch(1)

        for (i in 0 until productCount) {
            Thread(MyRunnable(MyProducer(queue), flag), "product${i}").start()
        }
        for (i in 0 until consumerCount) {
            Thread(MyRunnable(MyConsumer(queue), flag), "consumer${i}").start()
        }
        for (i in 0 until consumerCount) {
            Thread(MyRunnable(MyConsumerWithTime(queue, 3), flag), "consumerWithTime${i}").start()
        }

        flag.countDown()
    }
}

class Product(val name: String)

class MyQueue(private val size: Int) : Queue<Product> {
    private val deque: ArrayDeque<Product> = ArrayDeque(size)
    private val lock = Object()

    override fun product(p: Product) {
        synchronized(lock) {
            while (deque.size > size) {
                println("生产过剩，${Thread.currentThread().name}等待")
                lock.wait()
            }
            deque.addLast(p)
            println("${Thread.currentThread().name}生产了${p.name}")
            lock.notifyAll()
        }
    }

    override fun consume(): Product {
        synchronized(lock) {
            while (deque.isEmpty()) {
                println("产品不足，${Thread.currentThread().name}等待")
                lock.wait()
            }
            val p: Product = deque.removeFirst()
            println("${Thread.currentThread().name}消费了${p.name}")
            lock.notifyAll()
            return p
        }
    }

    override fun consumeWithTime(mills: Long): Product? {
        synchronized(lock) {
            val future: Long = System.currentTimeMillis() + mills
            var remain = mills
            while (deque.isEmpty() && remain > 0) {
                println("产品不足，${Thread.currentThread().name}等待${remain}")
                lock.wait(remain)
                remain = future - System.currentTimeMillis()
            }
            val p: Product? = if (deque.isEmpty()) null else deque.removeFirst()
            return if (p == null) {
                println("${Thread.currentThread().name}消费了个寂寞")
                null
            } else {
                println("${Thread.currentThread().name}消费了${p.name}")
                lock.notifyAll()
                p
            }
        }
    }
}

class MyProducer(private val queue: Queue<Product>) : Producer<Product>, Runnable {
    companion object {
        private val atomicInt = AtomicInteger(0)
    }

    override fun product(queue: Queue<Product>) {
        queue.product(Product("p_${atomicInt.getAndIncrement()}"))
    }

    override fun run() {
        while (true) {
            product(queue)
            Thread.sleep(10)
        }
    }
}

class MyConsumerWithTime(private val queue: Queue<Product>, private val mills: Long) :
    ConsumerWithTime<Product>, Runnable {
    override fun consumeWithTime(queue: Queue<Product>, mills: Long): Product? {
        if (mills < 0) {
            return queue.consume()
        }
        return queue.consumeWithTime(mills)
    }

    override fun run() {
        while (true) {
            consumeWithTime(queue, mills)
            Thread.sleep(10)
        }
    }
}

class MyConsumer(private val queue: Queue<Product>) : Consumer<Product>, Runnable {
    override fun consume(queue: Queue<Product>): Product {
        return queue.consume()
    }

    override fun run() {
        while (true) {
            consume(queue)
            Thread.sleep(10)
        }
    }
}