package com.lingo.github.performance.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class PerformanceAop {

    @Around("call(* com.lingo.github.app.BuglyInitializer.**(..))")
    fun getTime(joinPoint: ProceedingJoinPoint) {

    }
}