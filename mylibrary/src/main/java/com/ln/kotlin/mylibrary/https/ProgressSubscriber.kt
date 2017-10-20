package com.ln.kotlin.mylibrary.https

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * description: 不支持背压
 * Created by liNan on 2017/10/19 14:29

 */
class ProgressSubscriber<T> : Observer<T> {
    private lateinit var mDisposable: Disposable
    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
    }

    override fun onNext(t: T) {
    }

    override fun onSubscribe(d: Disposable) {
        mDisposable = d
    }

}