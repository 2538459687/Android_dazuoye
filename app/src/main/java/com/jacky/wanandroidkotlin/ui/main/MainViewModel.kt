package com.jacky.wanandroidkotlin.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jacky.wanandroidkotlin.base.BaseViewModel
import com.jacky.wanandroidkotlin.base.executeRequest
import com.jacky.wanandroidkotlin.model.repositry.UserRepository

/**
 * @Author:YumDreaming
 * @date :2024/5/30
 * desc  ：首页
 * record：
 */
class MainViewModel(application: Application) : BaseViewModel(application) {
    val mLogoutInfo: MutableLiveData<String> = MutableLiveData()
    private val mRepository by lazy { UserRepository() }

    fun logout() {
        executeRequest(request = { mRepository.logout() },
            onNext = { ok, data, msg ->
                if (ok) {
                    mLogoutInfo.value = "退出登录成功！"
                }
            })
    }
}