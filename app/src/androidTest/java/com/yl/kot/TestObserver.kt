package com.yl.kot

import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created on 2019/7/27.
 *
 * @author lshun
 * @version 1.0
 */
open class TestObserver<T> : Observer<T> {
    override fun onComplete() {
        Log.d(TAG, "onComplete -> ")
    }

    override fun onSubscribe(d: Disposable) {
        Log.d(TAG, "onSubscribe -> $d")
    }

    override fun onNext(t: T) {
        Log.d(TAG, "onNext -> $t")
    }

    override fun onError(e: Throwable) {
        Log.d(TAG, "onError -> ", e)
    }

    companion object {
        private const val TAG: String = "TestObserver"
    }
}