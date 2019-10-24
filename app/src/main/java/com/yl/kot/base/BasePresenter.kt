package com.yl.kot.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.yl.kot.data.remote.RemoteErrorHandler
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
abstract class BasePresenter<T : IBaseView>(view: T) : IBasePresenter<T>, CoroutineScope {

    protected var mView: T? = null
    private val mExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        mView?.let {
            RemoteErrorHandler.handle(it, throwable)
        }
    }
    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main + mExceptionHandler

    init {
        attachView(view)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onWindowCreated() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onWindowStarted() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onWindowResumed() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onWindowPaused() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onWindowStopped() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onWindowDestroyed() {
        destroy()
    }

    final override fun attachView(view: T) {
        this.mView = view
    }

    override fun detachView() {
        mView = null
    }

    override fun destroy() {
        cancel()
        detachView()
    }
}
