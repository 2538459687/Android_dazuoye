package com.jacky.wanandroidkotlin.model.local

import android.app.Application
import android.content.Context
import com.jacky.wanandroidkotlin.app.ApplicationKit
import io.reactivex.Observable

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：提供applicationContext
 * record：
 */
object ContextModel {

    /**
     * 获取Context
     */
    fun getApplicationContext(): Context {
        return ApplicationKit.mApplication as Context
    }

    fun getApplicationContextObservable(): Observable<Application> {
        val application = getApplicationContext() as Application
        return Observable.just(application)
    }
}