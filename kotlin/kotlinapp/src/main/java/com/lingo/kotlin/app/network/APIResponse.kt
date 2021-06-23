package com.lingo.kotlin.app.network

import com.lingo.kotlin.app.entity.Response
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

abstract class APIResponse<D> : SingleObserver<Response<D>> {
    abstract fun success(data: D)
    abstract fun failure(msg: String)

    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
        failure(e.message ?: "网络异常")
    }

    override fun onSuccess(t: Response<D>) {
        if (t.errorCode == 0) {
            success(t.data)
        } else {
            failure(t.errorMsg)
        }
    }
}