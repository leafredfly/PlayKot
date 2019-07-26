package com.yl.kot.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
abstract class BasePresenter<T : IBaseView>(view: T) : IBasePresenter<T> {

    protected var mView: T? = null
    private val mDisposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    init {
        attachView(view)
    }

    override fun onWindowCreated() {}

    override fun onWindowStarted() {}

    override fun onWindowResumed() {}

    override fun onWindowPaused() {}

    override fun onWindowStopped() {}

    override fun onWindowDestroyed() {
        destroy()
    }

    final override fun attachView(view: T) {
        this.mView = view
    }

    override fun detachView() {
        mView = null
    }

    override fun getView(): IBaseView? = mView

    override fun addDisposable(disposable: Disposable) {
        mDisposables.add(disposable)
    }

    override fun destroy() {
        mDisposables.dispose()
        detachView()
    }
}
