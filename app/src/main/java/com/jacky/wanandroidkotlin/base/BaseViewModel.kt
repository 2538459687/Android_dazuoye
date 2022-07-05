package com.jacky.wanandroidkotlin.base

import android.app.Application
import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.*
import com.jacky.wanandroidkotlin.app.GlobalLifecycleObserver
import com.jacky.wanandroidkotlin.model.api.dispatch
import com.jacky.wanandroidkotlin.model.api.isApiSuccess
import com.jacky.wanandroidkotlin.model.entity.WanResponse
import kotlinx.coroutines.*


/**
 * @author:Hzj
 * @date  :2019/6/24/024
 * desc  ：ViewModel基类
 * record：<p>suspend，用作修饰会被暂停的函数，被标记为 suspend 的函数只能运行在协程或者其他 suspend 函数当中。</p>
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    DefaultLifecycleObserver {

    //错误信息
    val mErrorMsg: MutableLiveData<String> = MutableLiveData()

    //加载框通道
    val mShowLoadingProgress: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


    @Suppress("UNCHECKED_CAST")
    @CallSuper
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate(owner: LifecycleOwner) {
        //初始化ViewModel绑定
        (owner as? IVMView<ViewModel>)?.startObserve?.invoke(this)
    }

    @CallSuper
//    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy(owner: LifecycleOwner) {

    }

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            Log.d("Thread", "launchOnUI:${Thread.currentThread().name}")
            block()
        }
    }
}

//网络请求封装
fun <T> BaseViewModel.executeRequest(
    showLoading: Boolean = false,
    request: suspend CoroutineScope.() -> WanResponse<T>,
    onNext: ((Boolean, T?, String?) -> Unit)? = null,
    onSubscribe: (() -> Unit)? = null,
    onError: (Throwable) -> Unit = { e ->
        e.dispatch(msgResult = { msg ->
            onNext?.invoke(false, null, msg)
            mErrorMsg.value = msg
        }, apiRefused = {
            //授权失败，返回登录页
            GlobalLifecycleObserver.restartApp(gotoLogin = true)
        })
    },
    onComplete: (() -> Unit)? = null
) {
    launchOnUI {
        tryCatch(
            tryBlock = {
                onSubscribe?.invoke()
                withContext(Dispatchers.IO) {
                    Log.d("Thread", "request:${Thread.currentThread().name}")
                    if (showLoading) {
                        mShowLoadingProgress.postValue(true)
                    }
                    request.invoke(this)
                }
            },
            catchBlock = { e -> onError.invoke(e) },
            finallyBlock = {
                if (showLoading) {
                    mShowLoadingProgress.value = false
                }
                onComplete?.invoke()
            },
            handleCancellationExceptionManually = true
        )?.let {
            Log.d("Thread", "response:${Thread.currentThread().name}")
            if (it.isApiSuccess()) {
                onNext?.invoke(true, it.data, null)
            } else {
                onNext?.invoke(false, null, it.errorMsg)
                mErrorMsg.value = it.errorMsg
            }
        }
    }
}

suspend fun <T> Deferred<WanResponse<T>?>?.observe(callback: (Boolean, T?, String?) -> Unit) {
    this?.await().apply {
        if (this != null && this.isApiSuccess()) {
            callback.invoke(true, data, null)
        } else {
            callback.invoke(false, null, this?.errorMsg)
        }
    }
}

//异步请求，封装协程的Async{},返回Deferred
suspend fun <T> BaseViewModel.executeRequestAsync(request: suspend CoroutineScope.() -> WanResponse<T>): Deferred<WanResponse<T>>? {
    return tryCatch(
        tryBlock = {
            viewModelScope.async(context = Dispatchers.IO) { request.invoke(this) }
        },
        catchBlock = { e ->
            e.dispatch(msgResult = { msg -> mErrorMsg.value = msg },
                apiRefused = {
                    //授权失败，返回登录页
                    GlobalLifecycleObserver.restartApp(gotoLogin = true)
                })
        },
        finallyBlock = {},
        handleCancellationExceptionManually = true
    )
}

suspend fun <T> tryCatch(
    tryBlock: suspend CoroutineScope.() -> T,
    catchBlock: (suspend CoroutineScope.(Throwable) -> Unit)? = null,
    finallyBlock: (suspend CoroutineScope.() -> Unit)? = null,
    handleCancellationExceptionManually: Boolean = false
): T? {
    return coroutineScope {
        Log.d("Thread", "tryCatch:${Thread.currentThread().name}")
        try {
            tryBlock()
        } catch (e: Exception) {
            if (e !is CancellationException || handleCancellationExceptionManually) {
                catchBlock?.invoke(this, e)
                null
            } else {
                throw e
            }
        } finally {
            finallyBlock?.invoke(this)
        }
    }
}

suspend fun <T> launchIO(block: suspend CoroutineScope.() -> T) {
    withContext(Dispatchers.IO) {
        block()
    }
}