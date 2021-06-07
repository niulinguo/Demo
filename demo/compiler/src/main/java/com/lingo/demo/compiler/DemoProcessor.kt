package com.lingo.demo.compiler

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@SupportedAnnotationTypes("java.lang.Override")
class DemoProcessor : AbstractProcessor() {
    override fun process(set: Set<TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
        printLog("====== Hello Processor ======")
        return false
    }

    private fun printLog(msg: String) {
        val logger = processingEnv.messager
        logger.printMessage(
            Diagnostic.Kind.NOTE,
            msg,
        )
    }
}