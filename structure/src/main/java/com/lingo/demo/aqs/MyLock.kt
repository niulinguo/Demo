package com.lingo.demo.aqs

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.AbstractQueuedSynchronizer
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock

class MyLock : Lock {

    private class Sync : AbstractQueuedSynchronizer() {
        /**
         * 判断占用状态
         */
        override fun isHeldExclusively(): Boolean {
            return state == 1
        }

        /**
         * 获得锁
         */
        override fun tryAcquire(arg: Int): Boolean {
            if (compareAndSetState(0, 1)) {
                exclusiveOwnerThread = Thread.currentThread()
                return true
            }
            return false
        }

        /**
         * 释放锁
         */
        override fun tryRelease(arg: Int): Boolean {
            if (state == 0) {
                throw IllegalMonitorStateException("释放锁的时候，没有锁")
            }
            exclusiveOwnerThread = null
            state = 0
            return true
        }
    }

    private val sync: Sync = Sync()

    override fun lock() {
        sync.acquire(1)
    }

    override fun lockInterruptibly() {
        TODO("Not yet implemented")
    }

    override fun tryLock(): Boolean {
        TODO("Not yet implemented")
    }

    override fun tryLock(time: Long, unit: TimeUnit): Boolean {
        TODO("Not yet implemented")
    }

    override fun unlock() {
        sync.release(1)
    }

    override fun newCondition(): Condition {
        TODO("Not yet implemented")
    }
}